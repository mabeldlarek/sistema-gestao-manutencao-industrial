package com.projetos.manutencao.identidade_acesso.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import com.projetos.manutencao.identidade_acesso.dto.FuncionarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.identidade_acesso.model.Funcionario;
import com.projetos.manutencao.identidade_acesso.repository.mongo.FuncionarioRepository;
import com.projetos.manutencao.identidade_acesso.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    
    private final FuncionarioRepository repository;
    @Autowired
    private ModelMapper modelMapper;


    public FuncionarioServiceImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    public Optional<Funcionario> findById(String id) {
        return repository.findById(id);
    }

    public Funcionario save(FuncionarioDTO funcionarioDTO) {
        if (funcionarioDTO == null) {
            throw new IllegalArgumentException("Objeto funcionarioDTO não pode ser nulo.");
        }

        Funcionario funcionario = modelMapper.map(funcionarioDTO, Funcionario.class);

        if (funcionario.getId() == null || funcionario.getId().isBlank()) {
            funcionario.setId(UUID.randomUUID().toString());
        }

        repository.save(funcionario);
        return repository.save(funcionario);
    }

    public void deleteById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }
        
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("funcionario não encontrado para exclusão: " + id);
        }
        
        repository.deleteById(id);
        
    }

    @Override
    public void update(String id, FuncionarioDTO funcionarioDTO) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (funcionarioDTO == null) {
            throw new IllegalArgumentException("Objeto funcionarioDTO não pode ser nulo.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Funcionario não encontrado para atualização: " + id);
        }

        Funcionario funcionario = modelMapper.map(funcionarioDTO, Funcionario.class);
        funcionario.setId(id);

        repository.save(funcionario);
    }

    @Override
    public Optional<Funcionario> findByMatricula(String matricula) {
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matricula não pode ser vazia.");
        }

        Optional<Funcionario> funcionario = Optional.ofNullable(repository.findByMatricula(matricula)
                .orElseThrow(() -> new NoSuchElementException(
                        "Funcionário não encontrado com a matrícula: " + matricula
                )));

        return funcionario;
    }

    public void vincularUsuarioAFuncionario(Funcionario funcionarioComUsuario){
        repository.save(funcionarioComUsuario);
    }

    @Override
    public Optional<Funcionario> findByUsuarioID(String usuarioId) {
        if (usuarioId == null || usuarioId.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }
        Optional<Funcionario> funcionario = Optional.ofNullable(repository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Funcionário não encontrado"
                )));

        return funcionario;
    }

}
