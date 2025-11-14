package com.projetos.manutencao.ativos.service;

import com.projetos.manutencao.ativos.DTO.MedidorDTO;
import com.projetos.manutencao.ativos.model.Medidor;

import java.util.List;
import java.util.Optional;

public interface MedidorService {
    Medidor create(String idEquipamento, MedidorDTO medidorDTO);

    List<Medidor> findByEquipamento(String equipamentoId);

    Optional<Medidor> findById(String id);

    void delete(String id);
}
