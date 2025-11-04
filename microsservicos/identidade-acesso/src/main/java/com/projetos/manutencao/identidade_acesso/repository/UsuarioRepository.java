package com.projetos.manutencao.identidade_acesso.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.manutencao.identidade_acesso.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);
    @Override
    Optional<Usuario> findById(UUID id);
}
