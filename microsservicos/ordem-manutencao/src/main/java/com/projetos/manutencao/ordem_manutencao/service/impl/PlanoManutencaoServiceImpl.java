package com.projetos.manutencao.ordem_manutencao.service.impl;
    
import java.util.*;

import com.projetos.manutencao.ordem_manutencao.DTO.PlanoManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.UpdateStatusOrdemDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.UpdateStatusPlanoDTO;
import com.projetos.manutencao.ordem_manutencao.enums.StatusOrdem;
import com.projetos.manutencao.ordem_manutencao.enums.StatusPlano;
import com.projetos.manutencao.ordem_manutencao.enums.UnidadeFrequencia;
import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;
import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;
import com.projetos.manutencao.ordem_manutencao.util.DateTimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.ordem_manutencao.repository.PlanoManutencaoRepository;
import com.projetos.manutencao.ordem_manutencao.service.PlanoManutencaoService;

@Service
public class PlanoManutencaoServiceImpl implements PlanoManutencaoService {

    private final PlanoManutencaoRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public PlanoManutencaoServiceImpl(PlanoManutencaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public PlanoManutencao criarPlano(PlanoManutencaoDTO planoManutencaoDTO) {
        PlanoManutencao planoManutencao = modelMapper.map(planoManutencaoDTO, PlanoManutencao.class);
        planoManutencao.setId(UUID.randomUUID().toString());

        if (planoManutencao.getGerarOMAutomatica()) {
            planoManutencao.setDataGeracaoAutomaticaOM(calcularProximaDataGeraçãoTempo(planoManutencao.getFrequenciaUnidade(), planoManutencao));
        }

        planoManutencao.setDataCriacao(new Date());

     
        return repository.save(planoManutencao);
    }

    @Override
    public Optional<PlanoManutencao> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public List<PlanoManutencao> listarTodos() {
        return repository.findAll();
    }

    @Override
    public PlanoManutencao atualizarPlano(String id, PlanoManutencaoDTO planoManutencaoDTO) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Plano de Manutenção não encontrado: " + id);
        }

        PlanoManutencao planoManutencao = modelMapper.map(planoManutencaoDTO, PlanoManutencao.class);
        planoManutencao.setId(id);

        repository.save(planoManutencao);

        return planoManutencao;
    }

    @Override
    public void deletarPlano(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Plano de Manutenção não encontrado: " + id);
        }

        repository.deleteById(id);
    }

    public List<PlanoManutencao> buscarPlanosAgendados(Date agora) {
        return repository.findByGerarOMAutomaticaTrueAndDataGeracaoAutomaticaOMLessThanEqual(agora);
    }

    private Date calcularProximaDataGeraçãoTempo(UnidadeFrequencia uf, PlanoManutencao pm) {
        if (uf == null) {
            throw new IllegalArgumentException("Unidade de Frequência não pode ser nula.");
        }

        if (pm == null) {
            throw new IllegalArgumentException("Plano de Manutenção não pode ser nulo.");
        }

        if (!repository.existsById(pm.getId())) {
            throw new NoSuchElementException("Plano de Manutenção não encontrado: " + pm.getId());
        }

        return DateTimeUtils.calcularProximaData(
                pm.getDataGeracaoAutomaticaOM(),
                uf,
                pm.getFrequenciaValor().intValue()
        );
    }

    @Override
    public void updateStatus(String idPm, UpdateStatusPlanoDTO status) {
        if (idPm == null || idPm.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (status == null) {
            throw new IllegalArgumentException("Status de PM  não pode ser nulo.");
        }

        if (!repository.existsById(idPm)) {
            throw new NoSuchElementException("Plano de Manutenção não encontrado: " + idPm);
        }

        PlanoManutencao pm = repository.findById(idPm).get();
        StatusPlano statusPlano = modelMapper.map(status, StatusPlano.class);

        if (pm != null) {
            pm.setStatus(statusPlano);
            repository.save(pm);
        }
    }

}