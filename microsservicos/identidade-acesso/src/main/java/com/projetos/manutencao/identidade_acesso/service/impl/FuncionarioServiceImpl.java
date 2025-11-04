package com.projetos.manutencao.identidade_acesso.service.impl;

import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.identidade_acesso.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.identidade_acesso.model.Funcionario;
import com.projetos.manutencao.identidade_acesso.repository.FuncionarioRepository;
import com.projetos.manutencao.identidade_acesso.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    
    private final FuncionarioRepository repository;
    private final UsuarioRepository usuarioRepository;


    public FuncionarioServiceImpl(FuncionarioRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    public Optional<Funcionario> findById(String id) {
        return repository.findById(id);
    }

    public Funcionario save(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Funcionario funcionario) {
        repository.save(funcionario);
    }
}
