package com.projetos.manutencao.ordem_manutencao.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.projetos.manutencao.ordem_manutencao.DTO.ExecucaoOrdemDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.ordem_manutencao.model.ExecucaoOrdem;
import com.projetos.manutencao.ordem_manutencao.repository.ExecucaoOrdemRepository;
import com.projetos.manutencao.ordem_manutencao.service.ExecucaoOrdemService;

@Service
public class ExecucaoOrdemServiceImpl implements ExecucaoOrdemService {

    private final ExecucaoOrdemRepository repository;
    @Autowired
    private ModelMapper modelMapper;

     public ExecucaoOrdemServiceImpl(ExecucaoOrdemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExecucaoOrdem criarExecucao(ExecucaoOrdemDTO execucaoOrdemDTO) {
        ExecucaoOrdem execucao = modelMapper.map(execucaoOrdemDTO, ExecucaoOrdem.class);
        execucao.setId(UUID.randomUUID().toString());

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
    public ExecucaoOrdem atualizarExecucao(String id, ExecucaoOrdemDTO execucaoOrdemDTO) {
        if (repository.existsById(id)) {
            ExecucaoOrdem execucao = modelMapper.map(execucaoOrdemDTO, ExecucaoOrdem.class);
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
