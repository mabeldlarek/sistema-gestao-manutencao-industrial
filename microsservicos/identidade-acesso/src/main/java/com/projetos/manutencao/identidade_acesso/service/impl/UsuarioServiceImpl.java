package com.projetos.manutencao.identidade_acesso.service.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.projetos.manutencao.identidade_acesso.dto.UsuarioDTO;
import com.projetos.manutencao.identidade_acesso.model.Role;
import com.projetos.manutencao.identidade_acesso.repository.RoleRepository;
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

    public UsuarioServiceImpl(
            UsuarioRepository repository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void update(UUID id, UsuarioDTO usuarioDTO) {

        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        UUID uuid;
        try {
            uuid = id;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ID informado não é válido.");
        }

        Usuario existente = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o id: " + id));

        existente.setNome(usuarioDTO.getNomeUsuario());
        existente.setEmail(usuarioDTO.getEmail());
        existente.setTipoUsuario(usuarioDTO.getTipoUsuario());

        if (usuarioDTO.getSenha() != null && !usuarioDTO.getSenha().isBlank()) {
            existente.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        }

        if (usuarioDTO.getRoles() != null && !usuarioDTO.getRoles().isEmpty()) {
            Set<Role> roles = usuarioDTO.getRoles().stream()
                    .map(role -> roleRepository.findByNameIgnoreCase(role.getName())
                            .orElseThrow(() -> new EntityNotFoundException("Role não encontrada: " + role.getName())))
                    .collect(Collectors.toSet());

            existente.setRoles(roles);
        }

        repository.save(existente);
    }


    @Override
    public Usuario findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o e-mail: " + email));
    }


    @Override
    public void deleteById(UUID id) {

        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        Usuario existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        repository.delete(existente);
    }


    @Override
    public Usuario findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }


    @Override
    public void save(UsuarioDTO usuarioDTO) {

        if (repository.existsByEmail(usuarioDTO.getEmail())) {
            throw new IllegalStateException("Já existe um usuário cadastrado com este e-mail.");
        }

        Set<Role> roles;
        if (usuarioDTO.getRoles() == null || usuarioDTO.getRoles().isEmpty()) {
            Role basic = roleRepository.findByNameIgnoreCase(Role.Values.BASIC.name())
                    .orElseThrow(() -> new EntityNotFoundException("Role BASIC não encontrada no banco."));

            roles = Set.of(basic);
        } else {
            roles = usuarioDTO.getRoles().stream()
                    .map(role -> roleRepository.findByNameIgnoreCase(role.getName())
                            .orElseThrow(() ->
                                    new EntityNotFoundException("Role não encontrada: " + role.getName())))
                    .collect(Collectors.toSet());
        }

        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        usuario.setRoles(roles);

        repository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

}
