package com.projetos.manutencao.ativos.service;

import java.util.List;

import com.projetos.manutencao.ativos.model.Equipamento;

public interface EquipamentoService {
    Equipamento create(Equipamento equipamento);

    List<Equipamento> findAll();

    Equipamento findById(String id);

    Equipamento update(String id, Equipamento equipamento);

    void delete(String id);
}
