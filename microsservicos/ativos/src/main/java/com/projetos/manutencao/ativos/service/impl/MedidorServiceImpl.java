package com.projetos.manutencao.ativos.service.impl;

import com.projetos.manutencao.ativos.DTO.MedidorDTO;
import com.projetos.manutencao.ativos.model.Criticidade;
import com.projetos.manutencao.ativos.model.Equipamento;
import com.projetos.manutencao.ativos.model.Medidor;
import com.projetos.manutencao.ativos.repository.EquipamentoRepository;
import com.projetos.manutencao.ativos.repository.MedidorRepository;
import com.projetos.manutencao.ativos.service.MedidorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        Medidor medidor = modelMapper.map(medidorDTO, Medidor.class);
        medidor.setId(UUID.randomUUID().toString());

        Equipamento equipamento = equipamentoRepository.findById(idEquipamento)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

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
        return repository.findByEquipamentoId(equipamentoId);
    }

    public Optional<Medidor> findById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}