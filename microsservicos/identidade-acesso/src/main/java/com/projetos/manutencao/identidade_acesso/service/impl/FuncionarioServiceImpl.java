package com.projetos.manutencao.identidade_acesso.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projetos.manutencao.identidade_acesso.model.Funcionario;
import com.projetos.manutencao.identidade_acesso.repository.FuncionarioRepository;
import com.projetos.manutencao.identidade_acesso.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    
    private final FuncionarioRepository repository;

    public FuncionarioServiceImpl(FuncionarioRepository repository) {
        this.repository = repository;
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
