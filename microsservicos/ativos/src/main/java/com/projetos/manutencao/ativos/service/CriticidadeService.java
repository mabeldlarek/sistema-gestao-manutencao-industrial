package com.projetos.manutencao.ativos.service;

import java.util.List;

import com.projetos.manutencao.ativos.DTO.CriticidadeDTO;
import com.projetos.manutencao.ativos.model.Criticidade;

public interface CriticidadeService {
    Criticidade create(String idEquipamento, CriticidadeDTO criticidade);

    List<Criticidade> findAll();

    Criticidade findById(String id);

    Criticidade update(String id, CriticidadeDTO criticidadeDTO);
    void delete(String id);
}
