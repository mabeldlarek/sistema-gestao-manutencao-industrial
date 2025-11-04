package com.projetos.manutencao.identidade_acesso.repository;

import com.projetos.manutencao.identidade_acesso.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}

