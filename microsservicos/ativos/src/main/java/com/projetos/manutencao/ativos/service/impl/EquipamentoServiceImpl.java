package com.projetos.manutencao.ativos.service.impl;


import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.projetos.manutencao.ativos.DTO.CriticidadeDTO;
import com.projetos.manutencao.ativos.DTO.EquipamentoDTO;
import com.projetos.manutencao.ativos.model.Criticidade;
import com.projetos.manutencao.ativos.repository.CriticidadeRepository;
import com.projetos.manutencao.ativos.repository.MedidorRepository;
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
    private final MedidorRepository medidorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EquipamentoServiceImpl(EquipamentoRepository repository, CriticidadeRepository criticidadeRepository, MedidorRepository medidorRepository) {
        this.repository = repository;
        this.criticidadeRepository = criticidadeRepository;
        this.medidorRepository = medidorRepository;
    }

    @Override
    public Equipamento create(EquipamentoDTO equipamentoDTO) {

        if (equipamentoDTO == null) {
            throw new IllegalArgumentException("Objeto EquipamentoDTO não pode ser nulo.");
        }

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
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Equipamento não encontrado: " + id));
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
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        Equipamento equipamento = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Equipamento não encontrado para exclusão:" + id));

        repository.deleteById(id);

        if (!equipamento.getCriticidadeID().isEmpty()) {
           Criticidade criticidade = criticidadeRepository.findById(equipamento.getCriticidadeID()).get();
           criticidadeRepository.delete(criticidade);
        }

        if(!equipamento.getMedidorIds().isEmpty()){
            medidorRepository.deleteByEquipamentoId(id);
        }

        String equipamentoCodigo = equipamento.getCodigo();
        if(!repository.findByPathStartingWithOrderByPathAsc(equipamentoCodigo).isEmpty()){
            repository.deleteByPathStartingWith(equipamentoCodigo);
        }

    }

    @Override
    public List<Equipamento> getTreeEquipamentos(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código não pode ser vazio.");
        }

        List<Equipamento> resultado = repository.findByPathStartingWithOrderByPathAsc(codigo);

        if (resultado == null || resultado.isEmpty()) {
            throw new NoSuchElementException("Nenhum equipamento encontrado iniciando com: " + codigo);
        }

        return resultado;
    }

    @Override
    public String findNivelCriticidadeEquipamento(String idEquipamento) {
        Equipamento equipamento = repository.findById(idEquipamento)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        return criticidadeRepository.findById(equipamento.getCriticidadeID())
                .map(Criticidade::getNivel)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Criticidade não encontrada: " + equipamento.getCriticidadeID()
                        ));
    }

    private void setPath(Equipamento equipamento){
        if (equipamento == null) {
            throw new IllegalArgumentException("Objeto Equipamento não pode ser nulo.");
        }

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
