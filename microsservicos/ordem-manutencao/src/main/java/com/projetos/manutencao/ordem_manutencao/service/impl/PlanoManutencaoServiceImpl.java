package com.projetos.manutencao.ordem_manutencao.service.impl;
    
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;
import com.projetos.manutencao.ordem_manutencao.repository.PlanoManutencaoRepository;
import com.projetos.manutencao.ordem_manutencao.service.PlanoManutencaoService;

@Service
public class PlanoManutencaoServiceImpl implements PlanoManutencaoService {

    private final PlanoManutencaoRepository repository;

    public PlanoManutencaoServiceImpl(PlanoManutencaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public PlanoManutencao criarPlano(PlanoManutencao plano) {
        if (plano.getDataCriacao() == null) {
            plano.setDataCriacao(new Date());
        }
     
        return repository.save(plano);
    }

    @Override
    public Optional<PlanoManutencao> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public List<PlanoManutencao> listarTodos() {
        return repository.findAll();
    }

    @Override
    public PlanoManutencao atualizarPlano(String id, PlanoManutencao plano) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Plano não encontrado");
        }
        plano.setId(id);
        return repository.save(plano);
    }

    @Override
    public void deletarPlano(String id) {
        repository.deleteById(id);
    }

}