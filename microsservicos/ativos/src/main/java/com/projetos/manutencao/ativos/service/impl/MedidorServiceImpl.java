package com.projetos.manutencao.ativos.service.impl;

import com.projetos.manutencao.ativos.DTO.MedidorDTO;
import com.projetos.manutencao.ativos.model.Equipamento;
import com.projetos.manutencao.ativos.model.Medidor;
import com.projetos.manutencao.ativos.repository.EquipamentoRepository;
import com.projetos.manutencao.ativos.repository.MedidorRepository;
import com.projetos.manutencao.ativos.service.MedidorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MedidorServiceImpl implements MedidorService {

    private final MedidorRepository repository;
    private final EquipamentoRepository equipamentoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public MedidorServiceImpl(MedidorRepository repository, EquipamentoRepository equipamentoRepository) {
        this.repository = repository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Medidor create(String idEquipamento, MedidorDTO medidorDTO) {

        if (idEquipamento == null || idEquipamento.isBlank()) {
            throw new IllegalArgumentException("ID do equipamento não pode ser vazio.");
        }

        if (medidorDTO == null) {
            throw new IllegalArgumentException("Objeto MdidorDTO não pode ser nulo.");
        }


        Medidor medidor = modelMapper.map(medidorDTO, Medidor.class);
        medidor.setId(UUID.randomUUID().toString());

        Equipamento equipamento = equipamentoRepository.findById(idEquipamento)
                .orElseThrow(() -> new NoSuchElementException("Equipamento não encontrado"));

        List<String> ids = equipamento.getMedidorIds();

        if (ids == null) {
            ids = new ArrayList<>();
        }

        ids.add(medidor.getId());

        equipamento.setMedidorIds(ids);

        equipamentoRepository.save(equipamento);

        medidor.setEquipamentoId(equipamento.getId());

        return repository.save(medidor);
    }

    public List<Medidor> findByEquipamento(String equipamentoId) {
        if (equipamentoId == null || equipamentoId.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        List<Medidor> resultado = repository.findByEquipamentoId(equipamentoId);

        if (resultado == null || resultado.isEmpty()) {
            throw new NoSuchElementException("Nenhum medidor encontrado para o equipamento informado");
        }

        return resultado;

    }

    public Medidor findById(String id) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Medidor não encontrado: " + id));
    }

    public void delete(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Medidor não encontrado para exclusão: " + id);
        }

        repository.deleteById(id);
    }

    @Override
    public List<Medidor> findAll() {
        return repository.findAll();
    }
}