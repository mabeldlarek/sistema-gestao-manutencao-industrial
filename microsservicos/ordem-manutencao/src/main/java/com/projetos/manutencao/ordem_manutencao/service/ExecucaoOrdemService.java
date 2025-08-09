package com.projetos.manutencao.ordem_manutencao.service;
import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.ordem_manutencao.model.ExecucaoOrdem;

public interface ExecucaoOrdemService {
    ExecucaoOrdem criarExecucao(ExecucaoOrdem execucao);
    Optional<ExecucaoOrdem> buscarPorId(String id);
    List<ExecucaoOrdem> listarTodas();
    ExecucaoOrdem atualizarExecucao(String id, ExecucaoOrdem execucao);
    void deletarExecucao(String id);
}
