package com.projetos.manutencao.ativos.service.impl;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.projetos.manutencao.ativos.DTO.CriticidadeDTO;
import com.projetos.manutencao.ativos.DTO.EquipamentoDTO;
import com.projetos.manutencao.ativos.model.Criticidade;
import com.projetos.manutencao.ativos.repository.CriticidadeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.ativos.model.Equipamento;
import com.projetos.manutencao.ativos.repository.EquipamentoRepository;
import com.projetos.manutencao.ativos.service.EquipamentoService;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EquipamentoServiceImpl implements EquipamentoService {

    private final EquipamentoRepository repository;
    private final CriticidadeRepository criticidadeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public EquipamentoServiceImpl(EquipamentoRepository repository, CriticidadeRepository criticidadeRepository) {
        this.repository = repository;
        this.criticidadeRepository = criticidadeRepository;
    }

    @Override
    public Equipamento create(EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = modelMapper.map(equipamentoDTO, Equipamento.class);

        if (equipamento.getId() == null || equipamento.getId().isBlank()) {
            equipamento.setId(UUID.randomUUID().toString());
        }

        setPath(equipamento);

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

    @Override
    public List<Equipamento> getTreeEquipamentos(String codigo) {
        return repository.findByPathStartingWithOrderByPathAsc(codigo);
    }

    @Override
    public String findNivelCriticidadeEquipamento(String idEquipamento) {
        Equipamento equipamento = repository.findById(idEquipamento)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        return criticidadeRepository.findById(equipamento.getCriticidadeID())
                .map(Criticidade::getNivel)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Criticidade não encontrada: " + equipamento.getCriticidadeID()
                ));
    }

    private void setPath(Equipamento equipamento){
        String path = "";

        if(equipamento.getParentID() != null){
            Equipamento equipamentoPai = repository.findById(equipamento.getParentID())
                    .orElseThrow(() -> new RuntimeException("Equipamento Pai não encontrado"));
            path = equipamentoPai.getPath() + "/" + equipamento.getCodigo();
            equipamento.setPath(path);
        } else
            equipamento.setPath(equipamento.getCodigo());
    }
}
