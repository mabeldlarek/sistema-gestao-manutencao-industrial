package com.projetos.manutencao.ativos.repository;

import com.projetos.manutencao.ativos.model.Medidor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedidorRepository extends MongoRepository<Medidor, String> {

    List<Medidor> findByEquipamentoId(String equipamentoId);
    void deleteByEquipamentoId(String id);

}