package com.projetos.manutencao.identidade_acesso.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.projetos.manutencao.identidade_acesso.dto.auth.FuncionarioDTO;
import com.projetos.manutencao.identidade_acesso.model.Funcionario;

public interface FuncionarioService {

    public List<Funcionario> findAll();

    public Optional<Funcionario> findById(String id);

    public Funcionario save(FuncionarioDTO funcionario);

    public void deleteById(String id);

    public void update(FuncionarioDTO funcionario);

    public Optional<Funcionario> findByMatricula(String matricula);
    public void vincularUsuarioAFuncionario(Funcionario funcionarioComUsuario);
    public Optional<Funcionario> findByUsuarioID(String usuarioId);
}
