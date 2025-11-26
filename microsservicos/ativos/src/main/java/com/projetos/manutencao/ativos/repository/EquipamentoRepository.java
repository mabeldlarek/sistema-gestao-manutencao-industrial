package com.projetos.manutencao.ativos.repository;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.projetos.manutencao.ativos.model.Equipamento;
import org.springframework.data.mongodb.repository.Query;

public interface EquipamentoRepository extends MongoRepository<Equipamento, String> {
    List<Equipamento> findByCodigo(String codigo);

    List<Equipamento> findByPathStartingWithOrderByPathAsc(String prefixo);
}
