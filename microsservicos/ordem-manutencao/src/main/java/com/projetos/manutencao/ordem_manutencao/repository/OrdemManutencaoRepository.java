package com.projetos.manutencao.ordem_manutencao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;

@Repository
public interface OrdemManutencaoRepository extends MongoRepository<OrdemManutencao, String> {}
