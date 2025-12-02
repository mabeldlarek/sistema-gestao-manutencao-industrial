package com.projetos.manutencao.ordem_manutencao.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

import com.projetos.manutencao.ordem_manutencao.DTO.*;
import com.projetos.manutencao.ordem_manutencao.enums.StatusExecucao;
import com.projetos.manutencao.ordem_manutencao.enums.StatusOrdem;
import com.projetos.manutencao.ordem_manutencao.feign.ProcedimentoClient;
import com.projetos.manutencao.ordem_manutencao.model.ChecklistItem;
import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;
import com.projetos.manutencao.ordem_manutencao.model.PeriodoTrabalho;
import com.projetos.manutencao.ordem_manutencao.repository.OrdemManutencaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.ordem_manutencao.model.ExecucaoOrdem;
import com.projetos.manutencao.ordem_manutencao.repository.ExecucaoOrdemRepository;
import com.projetos.manutencao.ordem_manutencao.service.ExecucaoOrdemService;

@Service
public class ExecucaoOrdemServiceImpl implements ExecucaoOrdemService {

    private final ExecucaoOrdemRepository repository;
    private final OrdemManutencaoRepository repositoryOM;
    private final ProcedimentoClient procedimentoClient;

    @Autowired
    private ModelMapper modelMapper;

     public ExecucaoOrdemServiceImpl(ExecucaoOrdemRepository repository, OrdemManutencaoRepository repositoryOM, ProcedimentoClient procedimentoClient) {
        this.repository = repository;
         this.repositoryOM = repositoryOM;
         this.procedimentoClient = procedimentoClient;
     }

    @Override
    public ExecucaoOrdem criarExecucao(ExecucaoOrdemDTO execucaoOrdemDTO) {
         //Execucao comeca com INICIADA (periodo de trabalho com inicio) lista n vazia
        //Data de inicio setada
        // Checklist gerado
        // Execucao iniciada muda a OM para EM_Execucao

        ExecucaoOrdem execucao = modelMapper.map(execucaoOrdemDTO, ExecucaoOrdem.class);
        execucao.setId(UUID.randomUUID().toString());
        execucao.setDataInicio(LocalDateTime.now());
        execucao.setStatusExecucao(StatusExecucao.INICIADA);

        List<PeriodoTrabalho> periodoTrabalhoList = new ArrayList<>();
        periodoTrabalhoList.add(new PeriodoTrabalho(LocalDateTime.now(), null));

        execucao.setPeriodosDeTrabalho(periodoTrabalhoList);

        List<ChecklistItem> checklistItemList = gerarCheckList(execucao.getOrdemManutencaoID());

        execucao.setChecklistItens(checklistItemList);

        OrdemManutencao om = repositoryOM.findById(execucao.getOrdemManutencaoID())
                .orElseThrow(() -> new EntityNotFoundException("Ordem não encontrada"));

        om.setStatus(StatusOrdem.EM_EXECUCAO);


        return repository.save(execucao);
    }

    @Override
    public Optional<ExecucaoOrdem> buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Ordem de Execução não encontrada : " + id);
        }

        return repository.findById(id);
    }

    @Override
    public List<ExecucaoOrdem> listarTodas() {
        return repository.findAll();
    }

    @Override
    public ExecucaoOrdem atualizarExecucao(String id, ExecucaoOrdemDTO execucaoOrdemDTO) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Ordem de Execução não encontrada para atualização: " + id);
        }

        if (repository.existsById(id)) {
            ExecucaoOrdem execucao = modelMapper.map(execucaoOrdemDTO, ExecucaoOrdem.class);
            execucao.setId(id);
            return repository.save(execucao);
        }
        throw new RuntimeException("Execução não encontrada para atualização");
    }

    @Override
    public void deletarExecucao(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Ordem de Execução não encontrada para atualização: " + id);
        }

        repository.deleteById(id);
    }

    @Override
    public void pausarExecucao(String id) {
        ExecucaoOrdem execucao = repository.findById(id).get();
        verificarCondicaoPausa(execucao);
        forListaPeriodoTrabalhoPausa(execucao);
     }

     private void forListaPeriodoTrabalhoPausa(ExecucaoOrdem execucao){
         List<PeriodoTrabalho> periodoTrabalhoList = execucao.getPeriodosDeTrabalho();

         for(PeriodoTrabalho pT : periodoTrabalhoList){
             if(pT.getInicio() != null && pT.getFim() == null){
                 pT.setFim(LocalDateTime.now());
                 execucao.setStatusExecucao(StatusExecucao.PAUSADA);
                 execucao.setId(execucao.getId());
                 execucao.setPeriodosDeTrabalho(periodoTrabalhoList);
                 repository.save(execucao);
                 return;
             }
         }
     }

     private void verificarCondicaoPausa(ExecucaoOrdem execucao){
         if (execucao.getStatusExecucao().equals(StatusExecucao.FINALIZADA)) {
             throw new RuntimeException("Execução já foi finalizada");
         }

         if(execucao.getStatusExecucao().equals(StatusExecucao.PAUSADA)){
             throw new RuntimeException("Execução ja foi pausada");
         }
     }

    @Override
    public void iniciarExecucao(String id) {

        // obter execucao pelo id
        // obter lista da execucao
        // impedir iniciar execucao sem ter finalizado um periodo de trabalho
        // impedir iniciar execucao que ja foi finalizada
        // quando encontrar uma lista que ja supriu as condições acima entao:
        // criar periodo de trabalho com inicio preenchido e fim nulo.

        if (repository.existsById(id)) {
            ExecucaoOrdem execucao = repository.findById(id).get();

            if(execucao.getStatusExecucao().equals(StatusExecucao.PAUSADA)){
                execucao.setStatusExecucao(StatusExecucao.RETOMADA);
            }

            if(execucao.getStatusExecucao().equals(StatusExecucao.FINALIZADA)){
                throw new RuntimeException("Execução já foi finalizada");
            }
            if(execucao.getStatusExecucao().equals(StatusExecucao.INICIADA)){
                throw new RuntimeException("Execução ja foi iniciada");
            }

            List<PeriodoTrabalho> periodoTrabalhoList = execucao.getPeriodosDeTrabalho();

            PeriodoTrabalho pT = new PeriodoTrabalho();

            pT.setInicio(LocalDateTime.now());

            periodoTrabalhoList.add(pT);

            execucao.setId(id);
            execucao.setPeriodosDeTrabalho(periodoTrabalhoList);

            repository.save(execucao);
        }

    }

    @Override
    public void finalizarExecucao(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Não foi encontrada a Ordem de Execução: " + id);
        }

        ExecucaoOrdem execucao = repository.findById(id).get();

        forListaPeriodoTrabalhoPausa(execucao);
        execucao.setStatusExecucao(StatusExecucao.FINALIZADA);

        OrdemManutencao om = repositoryOM.findById(execucao.getOrdemManutencaoID()).get();
        om.setDataFechamento(Date.from(Instant.now()));
        om.setStatus(StatusOrdem.CONCLUIDA);

        repository.save(execucao);
        repositoryOM.save(om);
    }

    private List<ChecklistItem> gerarCheckList(String idOM){
        //execucao possui a ordem
        // a ordem possui o procedimento com as strings do passo a passo
        //montar o objeto checklist descricao = string
        // todos os concluidos com false

        if (idOM == null || idOM.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(idOM)) {
            throw new NoSuchElementException("Ordem de Manutenção não encontrada para o checklist: " + idOM);
        }

        OrdemManutencao om = repositoryOM.findById(idOM).get();
        String idProcedimento = om.getProcedimentoID();

        ProcedimentoCheklistDTO listaPassos = procedimentoClient.buscar(idProcedimento);
        List<ChecklistItem> checklistItemList = new ArrayList<>();

        for(String passo : listaPassos.getPassosChecklist()){
            checklistItemList.add(new ChecklistItem(passo, false));
        }

        return checklistItemList;
    }


}
