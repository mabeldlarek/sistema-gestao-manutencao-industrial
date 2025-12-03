package com.projetos.manutencao.identidade_acesso.service;

import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.identidade_acesso.dto.FuncionarioDTO;
import com.projetos.manutencao.identidade_acesso.model.Funcionario;

public interface FuncionarioService {

    public List<Funcionario> findAll();

    public Optional<Funcionario> findById(String id);

    public Funcionario save(FuncionarioDTO funcionario);

    public void deleteById(String id);

    public void update(String id, FuncionarioDTO funcionario);

    public Optional<Funcionario> findByMatricula(String matricula);
    public void vincularUsuarioAFuncionario(Funcionario funcionarioComUsuario);
    public Optional<Funcionario> findByUsuarioID(String usuarioId);
}
