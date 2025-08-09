package com.projetos.manutencao.ordem_manutencao.service;
import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;

public interface  OrdemManutencaoService {
    OrdemManutencao create(OrdemManutencao ordem);
    Optional<OrdemManutencao> findById(String id);
    List<OrdemManutencao> findAll();
    OrdemManutencao update(String id, OrdemManutencao ordem);
    void delete(String id);
}
