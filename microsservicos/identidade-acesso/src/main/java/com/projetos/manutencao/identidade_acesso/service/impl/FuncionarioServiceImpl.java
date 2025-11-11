package com.projetos.manutencao.identidade_acesso.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.projetos.manutencao.identidade_acesso.dto.auth.FuncionarioDTO;
import com.projetos.manutencao.identidade_acesso.repository.UsuarioRepository;
import com.projetos.manutencao.identidade_acesso.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
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
        Funcionario funcionario = modelMapper.map(funcionarioDTO, Funcionario.class);
        repository.save(funcionario);
        return repository.save(funcionario);
    }

    public void deleteById(String id) {
        Optional<Funcionario> funcionario = repository.findById(id.toString());
        if (funcionario.isPresent()) {
            repository.deleteById(id);
        }
    }

    @Override
    public void update(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = repository.findByMatricula(funcionarioDTO.getMatricula())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Funcionário não encontrado com a matrícula: " + funcionarioDTO.getMatricula()
                ));

        funcionario.setCargo(funcionarioDTO.getCargo());
        funcionario.setEquipe(funcionarioDTO.getEquipe());
        funcionario.setEspecialidades(funcionarioDTO.getEspecialidades());
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setStatus(funcionarioDTO.getStatus());

        repository.save(funcionario);
    }

    @Override
    public Optional<Funcionario> findByMatricula(String matricula) {
        Optional<Funcionario> funcionario = Optional.ofNullable(repository.findByMatricula(matricula)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Funcionário não encontrado com a matrícula: " + matricula
                )));

        return funcionario;
    }

    public void vincularUsuarioAFuncionario(Funcionario funcionarioComUsuario){
        repository.save(funcionarioComUsuario);
    }

    @Override
    public Optional<Funcionario> findByUsuarioID(String usuarioId) {
        Optional<Funcionario> funcionario = Optional.ofNullable(repository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Funcionário não encontrado"
                )));

        return funcionario;
    }

}
