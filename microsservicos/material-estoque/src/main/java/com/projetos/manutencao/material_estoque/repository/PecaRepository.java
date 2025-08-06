package com.projetos.manutencao.material_estoque.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.manutencao.material_estoque.model.Peca;

public interface PecaRepository extends JpaRepository<Peca, UUID> {}    

