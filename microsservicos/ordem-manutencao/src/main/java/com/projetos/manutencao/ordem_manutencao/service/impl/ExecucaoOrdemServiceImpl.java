package com.projetos.manutencao.ordem_manutencao.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.projetos.manutencao.ordem_manutencao.DTO.ExecucaoOrdemDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.ExecucaoOrdemPausarDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.PeriodoTrabalhoDTO;
import com.projetos.manutencao.ordem_manutencao.enums.StatusExecucao;
import com.projetos.manutencao.ordem_manutencao.model.PeriodoTrabalho;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.ordem_manutencao.model.ExecucaoOrdem;
import com.projetos.manutencao.ordem_manutencao.repository.ExecucaoOrdemRepository;
import com.projetos.manutencao.ordem_manutencao.service.ExecucaoOrdemService;

@Service
public class ExecucaoOrdemServiceImpl implements ExecucaoOrdemService {

    private final ExecucaoOrdemRepository repository;
    @Autowired
    private ModelMapper modelMapper;

     public ExecucaoOrdemServiceImpl(ExecucaoOrdemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExecucaoOrdem criarExecucao(ExecucaoOrdemDTO execucaoOrdemDTO) {
         //Execucao comeca com INICIADA (periodo de trabalho com inicio) lista n vazia
        //Data de inicio setada
        ExecucaoOrdem execucao = modelMapper.map(execucaoOrdemDTO, ExecucaoOrdem.class);
        execucao.setId(UUID.randomUUID().toString());
        execucao.setDataInicio(LocalDateTime.now());
        execucao.setStatusExecucao(StatusExecucao.INICIADA);

        List<PeriodoTrabalho> periodoTrabalhoList = new ArrayList<>();
        periodoTrabalhoList.add(new PeriodoTrabalho(LocalDateTime.now(), null));

        execucao.setPeriodosDeTrabalho(periodoTrabalhoList);

        return repository.save(execucao);
    }

    @Override
    public Optional<ExecucaoOrdem> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public List<ExecucaoOrdem> listarTodas() {
        return repository.findAll();
    }

    @Override
    public ExecucaoOrdem atualizarExecucao(String id, ExecucaoOrdemDTO execucaoOrdemDTO) {
        if (repository.existsById(id)) {
            ExecucaoOrdem execucao = modelMapper.map(execucaoOrdemDTO, ExecucaoOrdem.class);
            execucao.setId(id);
            return repository.save(execucao);
        }
        throw new RuntimeException("Execução não encontrada para atualização");
    }

    @Override
    public void deletarExecucao(String id) {
        repository.deleteById(id);
    }

    @Override
    public void pausarExecucao(String id) {
        ExecucaoOrdem execucao = repository.findById(id).get();

        if (execucao.getStatusExecucao().equals(StatusExecucao.FINALIZADA)) {
            throw new RuntimeException("Execução já foi finalizada");
        }

        if(execucao.getStatusExecucao().equals(StatusExecucao.PAUSADA)){
            throw new RuntimeException("Execução ja foi pausada");
        }

        List<PeriodoTrabalho> periodoTrabalhoList = execucao.getPeriodosDeTrabalho();

        for(PeriodoTrabalho pT : periodoTrabalhoList){
            if(pT.getInicio() != null && pT.getFim() == null){
                pT.setFim(LocalDateTime.now());
            }
        }

        execucao.setStatusExecucao(StatusExecucao.PAUSADA);
        execucao.setId(id);
        execucao.setPeriodosDeTrabalho(periodoTrabalhoList);

        repository.save(execucao);
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

}
