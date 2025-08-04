package com.projetos.manutencao.identidade_acesso.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projetos.manutencao.identidade_acesso.model.Funcionario;

@Repository
public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {

}