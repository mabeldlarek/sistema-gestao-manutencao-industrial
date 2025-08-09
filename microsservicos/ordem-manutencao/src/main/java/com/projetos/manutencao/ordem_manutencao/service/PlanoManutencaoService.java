package com.projetos.manutencao.ordem_manutencao.service;

import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;

public interface PlanoManutencaoService {
    PlanoManutencao criarPlano(PlanoManutencao plano);
    Optional<PlanoManutencao> buscarPorId(String id);
    List<PlanoManutencao> listarTodos();
    PlanoManutencao atualizarPlano(String id, PlanoManutencao plano);
    void deletarPlano(String id);
}