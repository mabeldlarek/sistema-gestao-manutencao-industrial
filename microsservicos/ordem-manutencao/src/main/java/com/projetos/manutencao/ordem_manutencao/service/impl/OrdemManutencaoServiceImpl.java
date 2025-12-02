package com.projetos.manutencao.ordem_manutencao.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.projetos.manutencao.ordem_manutencao.DTO.FuncionarioDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.OrdemManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.PlanoManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.UpdateStatusOrdemDTO;
import com.projetos.manutencao.ordem_manutencao.enums.Prioridade;
import com.projetos.manutencao.ordem_manutencao.enums.StatusOrdem;
import com.projetos.manutencao.ordem_manutencao.feign.EquipamentoClient;
import com.projetos.manutencao.ordem_manutencao.feign.FuncionarioClient;
import com.projetos.manutencao.ordem_manutencao.feign.ProcedimentoClient;
import com.projetos.manutencao.ordem_manutencao.model.ExecucaoOrdem;
import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;
import com.projetos.manutencao.ordem_manutencao.repository.PlanoManutencaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;
import com.projetos.manutencao.ordem_manutencao.repository.OrdemManutencaoRepository;
import com.projetos.manutencao.ordem_manutencao.service.OrdemManutencaoService;


@Service
public class OrdemManutencaoServiceImpl implements OrdemManutencaoService {

    private final OrdemManutencaoRepository repository;
    private final PlanoManutencaoRepository planoManutencaoRepository;
    private final EquipamentoClient equipamentoClient;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrdemManutencaoRepository repo;

    public OrdemManutencaoServiceImpl(OrdemManutencaoRepository repository, PlanoManutencaoRepository planoManutencaoRepository, EquipamentoClient equipamentoClient) {
        this.repository = repository;
        this.planoManutencaoRepository = planoManutencaoRepository;
        this.equipamentoClient = equipamentoClient;
    }

    @Override
    public OrdemManutencao create(OrdemManutencaoDTO ordemManutencaoDTO) {
        OrdemManutencao ordemManutencao = modelMapper.map(ordemManutencaoDTO, OrdemManutencao.class);
        ordemManutencao.setId(UUID.randomUUID().toString());
        return repository.save(ordemManutencao);
    }

    @Override
    public Optional<OrdemManutencao> findById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("OM não encontrada: " + id);
        }
        return repository.findById(id);
    }

    @Override
    public List<OrdemManutencao> findAll() {
        return repository.findAll();
    }

    @Override
    public OrdemManutencao update(String id, OrdemManutencaoDTO ordemManutencaoDTO) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("OM não encontrada para atualização: " + id);
        }

        OrdemManutencao ordemManutencao = modelMapper.map(ordemManutencaoDTO, OrdemManutencao.class);

        if (repository.existsById(id)) {
            repository.save(ordemManutencao);
        }

        return ordemManutencao;
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("OM não encontrada para exclusão: " + id);
        }

        repository.deleteById(id);
    }

    public OrdemManutencao gerarOM(String idPM) {
        if (idPM == null || idPM.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!planoManutencaoRepository.existsById(idPM)) {
            throw new NoSuchElementException("Plano de Manutenção não encontrada para gerar OM: " + idPM);
        }

        PlanoManutencao pm = planoManutencaoRepository.findById(idPM).get();

        OrdemManutencao om = OrdemManutencao.builder()
                .numeroOS(UUID.randomUUID().toString())
                .planoManutencaoID(pm.getId())
                .equipamentoID(pm.getEquipamentoID())
                .procedimentoID(pm.getProcedimentoID())
                .status(StatusOrdem.ABERTA)
                .dataAbertura(new Date())
                .responsavelID(selecionaResponsavelOM(idPM))
                .prioridade(getPrioridadeCriticidadeEquipamento(pm.getEquipamentoID()))
                .build();

        return repo.save(om);
    }

    @Override
    public void updateStatus(String idOm, UpdateStatusOrdemDTO status) {
        if (idOm == null || idOm.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(idOm)) {
            throw new NoSuchElementException("OM não encontrada para atualização: " + idOm);
        }

        OrdemManutencao om = repository.findById(idOm).get();
        StatusOrdem statusOrdem = modelMapper.map(status, StatusOrdem.class);

        if (om != null) {
            om.setStatus(statusOrdem);
            repository.save(om);
        }
    }

    private String selecionaResponsavelOM(String idPlano){
        if (idPlano == null || idPlano.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }
        if (!planoManutencaoRepository.existsById(idPlano)) {
            throw new NoSuchElementException("Plano de Manutenção não encontrada para selecionar responsável: " + idPlano);
        }

        PlanoManutencao pm = planoManutencaoRepository.findById(idPlano).get();
        List<String> idsResponsaveis = pm.getResponsaveisPadraoID();

        for(String funcionarioId : idsResponsaveis){
            long numeroOrdensPorFuncionario = repository.countByResponsavelID(funcionarioId);

            if(numeroOrdensPorFuncionario < 3) {
                return funcionarioId;
            }
        }

        return "";
    }

    private Prioridade getPrioridadeCriticidadeEquipamento(String idEquipamento){
        String nivel = equipamentoClient.getNivelCriticidadeEquipamento(idEquipamento);

        switch (nivel) {
            case "A":
                return Prioridade.CRITICA;
            case "B":
                return Prioridade.ALTA;
            case "C":
                return Prioridade.MEDIA;
            case "D":
                return Prioridade.BAIXA;
        }

        return null;
    }

}