package com.projetos.manutencao.ativos.service.impl;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.projetos.manutencao.ativos.model.Equipamento;
import com.projetos.manutencao.ativos.repository.EquipamentoRepository;
import com.projetos.manutencao.ativos.service.EquipamentoService;

@Service
public class EquipamentoServiceImpl implements EquipamentoService {

    private final EquipamentoRepository repository;

    public EquipamentoServiceImpl(EquipamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Equipamento create(Equipamento equipamento) {
        if (equipamento.getId() == null || equipamento.getId().isBlank()) {
            equipamento.setId(UUID.randomUUID().toString());
        }
        return repository.save(equipamento);
    }

    @Override
    public List<Equipamento> findAll() {
        return repository.findAll();
    }

    @Override
    public Equipamento findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Equipamento update(String id, Equipamento equipamento) {
        if (!repository.existsById(id)) {
            return null;
        }
        equipamento.setId(id);
        return repository.save(equipamento);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
