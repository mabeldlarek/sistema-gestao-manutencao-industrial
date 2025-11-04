package com.projetos.manutencao.ordem_manutencao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;
import com.projetos.manutencao.ordem_manutencao.repository.OrdemManutencaoRepository;
import com.projetos.manutencao.ordem_manutencao.service.OrdemManutencaoService;


@Service
public class OrdemManutencaoServiceImpl implements OrdemManutencaoService {

    private final OrdemManutencaoRepository repository;

    public OrdemManutencaoServiceImpl(OrdemManutencaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrdemManutencao create(OrdemManutencao ordem) {
        return repository.save(ordem);
    }

    @Override
    public Optional<OrdemManutencao> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<OrdemManutencao> findAll() {
        return repository.findAll();
    }

    @Override
    public OrdemManutencao update(String id, OrdemManutencao ordem) {
        return repository.findById(id).map(existing -> {
            existing.setNumeroOS(ordem.getNumeroOS());
            existing.setEquipamentoID(ordem.getEquipamentoID());
            existing.setDescricaoProblema(ordem.getDescricaoProblema());
            existing.setTipoManutencao(ordem.getTipoManutencao());
            existing.setStatus(ordem.getStatus());
            existing.setPrioridade(ordem.getPrioridade());
            existing.setDataAbertura(ordem.getDataAbertura());
            existing.setDataFechamento(ordem.getDataFechamento());
            existing.setSolicitanteID(ordem.getSolicitanteID());
            existing.setResponsavelID(ordem.getResponsavelID());
            existing.setProcedimentoID(ordem.getProcedimentoID());
            existing.setObservacoes(ordem.getObservacoes());
            existing.setCustoEstimado(ordem.getCustoEstimado());
            existing.setCustoReal(ordem.getCustoReal());
            existing.setTempoParadaEstimado(ordem.getTempoParadaEstimado());
            existing.setTempoParadaReal(ordem.getTempoParadaReal());
            existing.setModoFalhaID(ordem.getModoFalhaID());
            existing.setCausaRaizID(ordem.getCausaRaizID());
            return repository.save(existing);
        }).orElseGet(() -> {
            ordem.setId(id);
            return repository.save(ordem);
        });
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }


}