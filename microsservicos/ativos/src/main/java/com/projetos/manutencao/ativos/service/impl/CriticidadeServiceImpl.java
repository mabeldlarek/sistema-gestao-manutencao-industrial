package com.projetos.manutencao.ativos.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.projetos.manutencao.ativos.model.Criticidade;
import com.projetos.manutencao.ativos.repository.CriticidadeRepository;
import com.projetos.manutencao.ativos.service.CriticidadeService;

@Service
public class CriticidadeServiceImpl implements CriticidadeService {

    private final CriticidadeRepository repository;

    public CriticidadeServiceImpl(CriticidadeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Criticidade create(Criticidade criticidade) {
        if (criticidade.getId() == null || criticidade.getId().isBlank()) {
            criticidade.setId(UUID.randomUUID().toString());
        }
        return repository.save(criticidade);
    }

    @Override
    public List<Criticidade> findAll() {
        return repository.findAll();
    }

    @Override
    public Criticidade findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Criticidade update(String id, Criticidade criticidade) {
        if (!repository.existsById(id)) return null;
        criticidade.setId(id);
        return repository.save(criticidade);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
