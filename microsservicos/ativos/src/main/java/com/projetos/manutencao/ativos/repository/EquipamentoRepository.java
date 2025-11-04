package com.projetos.manutencao.ativos.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projetos.manutencao.ativos.model.Equipamento;

public interface EquipamentoRepository extends MongoRepository<Equipamento, String> {
    List<Equipamento> findByCodigo(String codigo);
}