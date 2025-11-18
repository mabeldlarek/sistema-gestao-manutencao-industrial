package com.projetos.manutencao.ordem_manutencao.service;
import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.ordem_manutencao.DTO.OrdemManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;

public interface  OrdemManutencaoService {
    OrdemManutencao create(OrdemManutencaoDTO ordemManutencaoDTO);
    Optional<OrdemManutencao> findById(String id);
    List<OrdemManutencao> findAll();
    OrdemManutencao update(String id, OrdemManutencaoDTO ordemManutencaoDTO);
    void delete(String id);
}
