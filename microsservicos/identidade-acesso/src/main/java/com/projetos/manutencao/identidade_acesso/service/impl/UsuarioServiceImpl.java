package com.projetos.manutencao.identidade_acesso.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.projetos.manutencao.identidade_acesso.model.Usuario;
import com.projetos.manutencao.identidade_acesso.repository.UsuarioRepository;
import com.projetos.manutencao.identidade_acesso.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    @Override
    public void update(Usuario usuarioAtualizado) {
        Usuario existente = repository.findByEmail(usuarioAtualizado.getEmail())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        existente.setNome(usuarioAtualizado.getNome());
        existente.setEmail(usuarioAtualizado.getEmail());
        existente.setTipoUsuario(usuarioAtualizado.getTipoUsuario());
        existente.setSenha(usuarioAtualizado.getSenha());
        
        repository.save(existente);
    }

    @Override
    public Usuario findByEmail(String email) {
        return repository.findByEmail(email).get();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Usuario findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(Usuario Usuario) {
        repository.save(Usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }
}
