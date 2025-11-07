package com.projetos.manutencao.identidade_acesso.service.impl;

import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.identidade_acesso.dto.auth.FuncionarioDTO;
import com.projetos.manutencao.identidade_acesso.model.Usuario;
import com.projetos.manutencao.identidade_acesso.repository.UsuarioRepository;
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
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;


    public FuncionarioServiceImpl(FuncionarioRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    public Optional<Funcionario> findById(Long id) {
        return repository.findById(id);
    }

    public Funcionario save(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = modelMapper.map(funcionarioDTO, Funcionario.class);
        funcionario.setCargo(funcionarioDTO.getCargo());
        funcionario.setEquipe(funcionarioDTO.getCargo());
        funcionario.setEspecialidades(funcionarioDTO.getEspecialidades());
        funcionario.setNome(funcionarioDTO.getCargo());
        funcionario.setStatus(funcionarioDTO.getStatus());

        repository.save(funcionario);
        return repository.save(funcionario);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
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
}
