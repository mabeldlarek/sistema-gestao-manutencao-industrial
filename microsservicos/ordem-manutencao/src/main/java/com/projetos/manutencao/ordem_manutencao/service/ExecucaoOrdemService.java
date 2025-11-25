package com.projetos.manutencao.ordem_manutencao.service;
import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.ordem_manutencao.DTO.ExecucaoOrdemDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.ExecucaoOrdemPausarDTO;
import com.projetos.manutencao.ordem_manutencao.model.ExecucaoOrdem;

public interface ExecucaoOrdemService {
    ExecucaoOrdem criarExecucao(ExecucaoOrdemDTO execucaoOrdemDTO);
    Optional<ExecucaoOrdem> buscarPorId(String id);
    List<ExecucaoOrdem> listarTodas();
    ExecucaoOrdem atualizarExecucao(String id, ExecucaoOrdemDTO execucaoOrdemDTO);
    void deletarExecucao(String id);
    void pausarExecucao(String id);
    void iniciarExecucao(String id);
}
