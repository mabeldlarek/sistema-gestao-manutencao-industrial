package com.projetos.manutencao.conteudo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projetos.manutencao.conteudo.model.Procedimento;

@Repository
public interface ProcedimentoRepository extends MongoRepository<Procedimento, String> {
}