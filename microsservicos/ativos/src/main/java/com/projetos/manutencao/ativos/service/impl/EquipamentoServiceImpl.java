package com.projetos.manutencao.ativos.service.impl;


import java.util.List;
import java.util.UUID;

import com.projetos.manutencao.ativos.DTO.EquipamentoDTO;
import com.projetos.manutencao.ativos.model.Criticidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.ativos.model.Equipamento;
import com.projetos.manutencao.ativos.repository.EquipamentoRepository;
import com.projetos.manutencao.ativos.service.EquipamentoService;

@Service
public class EquipamentoServiceImpl implements EquipamentoService {

    private final EquipamentoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public EquipamentoServiceImpl(EquipamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Equipamento create(EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = modelMapper.map(equipamentoDTO, Equipamento.class);

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
    public Equipamento update(String id, EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = modelMapper.map(equipamentoDTO, Equipamento.class);
        equipamento.setId(id);

        if (!repository.existsById(id)) {
            return null;
        }

        return repository.save(equipamento);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
