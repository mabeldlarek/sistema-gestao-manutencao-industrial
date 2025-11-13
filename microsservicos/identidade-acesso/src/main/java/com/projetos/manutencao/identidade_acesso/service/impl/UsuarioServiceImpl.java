package com.projetos.manutencao.identidade_acesso.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.projetos.manutencao.identidade_acesso.dto.auth.FuncionarioDTO;
import com.projetos.manutencao.identidade_acesso.dto.auth.UsuarioDTO;
import com.projetos.manutencao.identidade_acesso.model.Funcionario;
import com.projetos.manutencao.identidade_acesso.model.Role;
import com.projetos.manutencao.identidade_acesso.repository.RoleRepository;
import com.projetos.manutencao.identidade_acesso.repository.mongo.FuncionarioRepository;
import com.projetos.manutencao.identidade_acesso.service.FuncionarioService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.identidade_acesso.model.Usuario;
import com.projetos.manutencao.identidade_acesso.repository.UsuarioRepository;
import com.projetos.manutencao.identidade_acesso.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.repository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void update(UsuarioDTO usuarioAtualizado) {
        Usuario existente = repository.findByEmail(usuarioAtualizado.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o e-mail: " + usuarioAtualizado.getEmail()));

        existente.setNome(usuarioAtualizado.getNomeUsuario());
        existente.setEmail(usuarioAtualizado.getEmail());
        existente.setTipoUsuario(usuarioAtualizado.getTipoUsuario());
        existente.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
        repository.save(existente);
    }

    @Override
    public Usuario findByEmail(String email) {
        return repository.findByEmail(email).get();
    }

    @Override
    public void deleteById(UUID id) {
        Usuario existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        repository.deleteById(id);
    }

    @Override
    public Usuario findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(UsuarioDTO usuarioDTO) {
        var basicRole = roleRepository.findByNameIgnoreCase(Role.Values.BASIC.name());
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        usuario.setRoles(Set.of(basicRole));
        repository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

}
