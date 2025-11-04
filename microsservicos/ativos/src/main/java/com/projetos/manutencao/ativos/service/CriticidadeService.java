package com.projetos.manutencao.ativos.service;

import java.util.List;

import com.projetos.manutencao.ativos.model.Criticidade;

public interface CriticidadeService {
    Criticidade create(Criticidade criticidade);

    List<Criticidade> findAll();

    Criticidade findById(String id);

    Criticidade update(String id, Criticidade criticidade);

    void delete(String id);
}
