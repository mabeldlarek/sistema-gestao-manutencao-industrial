package com.projetos.manutencao.ativos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projetos.manutencao.ativos.model.Criticidade;

public interface CriticidadeRepository extends MongoRepository<Criticidade, String> {
}
