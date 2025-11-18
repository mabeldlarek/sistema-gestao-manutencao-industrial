package com.projetos.manutencao.ordem_manutencao.service;

import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.ordem_manutencao.DTO.PlanoManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;

public interface PlanoManutencaoService {
    PlanoManutencao criarPlano(PlanoManutencaoDTO plano);
    Optional<PlanoManutencao> buscarPorId(String id);
    List<PlanoManutencao> listarTodos();
    PlanoManutencao atualizarPlano(String id, PlanoManutencaoDTO plano);
    void deletarPlano(String id);
}