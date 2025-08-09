package com.projetos.manutencao.ordem_manutencao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projetos.manutencao.ordem_manutencao.model.ExecucaoOrdem;

public interface ExecucaoOrdemRepository extends MongoRepository<ExecucaoOrdem, String> {
}
