package com.projetos.manutencao.material_estoque.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.manutencao.material_estoque.model.PecaConsumo;

public interface PecaConsumoRepository extends JpaRepository<PecaConsumo, UUID> {}
    

