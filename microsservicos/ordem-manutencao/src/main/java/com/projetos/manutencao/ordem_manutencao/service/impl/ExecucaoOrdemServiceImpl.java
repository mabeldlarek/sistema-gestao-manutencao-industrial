package com.projetos.manutencao.ordem_manutencao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projetos.manutencao.ordem_manutencao.model.ExecucaoOrdem;
import com.projetos.manutencao.ordem_manutencao.repository.ExecucaoOrdemRepository;
import com.projetos.manutencao.ordem_manutencao.service.ExecucaoOrdemService;

@Service
public class ExecucaoOrdemServiceImpl implements ExecucaoOrdemService {

    private final ExecucaoOrdemRepository repository;

     public ExecucaoOrdemServiceImpl(ExecucaoOrdemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExecucaoOrdem criarExecucao(ExecucaoOrdem execucao) {
        return repository.save(execucao);
    }

    @Override
    public Optional<ExecucaoOrdem> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public List<ExecucaoOrdem> listarTodas() {
        return repository.findAll();
    }

    @Override
    public ExecucaoOrdem atualizarExecucao(String id, ExecucaoOrdem execucao) {
        if (repository.existsById(id)) {
            execucao.setId(id);
            return repository.save(execucao);
        }
        throw new RuntimeException("Execução não encontrada para atualização");
    }

    @Override
    public void deletarExecucao(String id) {
        repository.deleteById(id);
    }
}
