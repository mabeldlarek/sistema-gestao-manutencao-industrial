package com.projetos.manutencao.ordem_manutencao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;

import java.util.Date;
import java.util.List;

public interface PlanoManutencaoRepository extends MongoRepository<PlanoManutencao, String> {

    List<PlanoManutencao> findByGerarOMAutomaticaTrueAndDataGeracaoAutomaticaOMLessThanEqual(Date data);

}
