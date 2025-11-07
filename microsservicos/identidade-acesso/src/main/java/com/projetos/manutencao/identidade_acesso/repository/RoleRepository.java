package com.projetos.manutencao.identidade_acesso.repository;

import com.projetos.manutencao.identidade_acesso.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    Role findByNameIgnoreCase(String name);
}

