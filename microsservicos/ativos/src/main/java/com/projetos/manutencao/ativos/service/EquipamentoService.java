package com.projetos.manutencao.ativos.service;

import java.util.List;
import java.util.Map;

import com.projetos.manutencao.ativos.DTO.EquipamentoDTO;
import com.projetos.manutencao.ativos.model.Equipamento;

public interface EquipamentoService {
    Equipamento create(EquipamentoDTO equipamentoDTO);

    List<Equipamento> findAll();

    Equipamento findById(String id);

    Equipamento update(String id, EquipamentoDTO equipamentoDTO);

    void delete(String id);

    List<Equipamento> getTreeEquipamentos(String codigo);
    String findNivelCriticidadeEquipamento(String idCriticidade);
}
