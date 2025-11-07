package com.projetos.manutencao.identidade_acesso.repository.mongo;

import com.projetos.manutencao.identidade_acesso.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projetos.manutencao.identidade_acesso.model.Funcionario;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends MongoRepository<Funcionario, Long> {
    Optional<Funcionario> findByMatricula(String email);

}