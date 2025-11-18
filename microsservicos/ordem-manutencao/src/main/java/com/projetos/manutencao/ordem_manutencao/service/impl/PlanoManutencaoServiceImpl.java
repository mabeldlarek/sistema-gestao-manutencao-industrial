package com.projetos.manutencao.ordem_manutencao.service.impl;
    
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.projetos.manutencao.ordem_manutencao.DTO.PlanoManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;
import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;
import com.projetos.manutencao.ordem_manutencao.repository.PlanoManutencaoRepository;
import com.projetos.manutencao.ordem_manutencao.service.PlanoManutencaoService;

@Service
public class PlanoManutencaoServiceImpl implements PlanoManutencaoService {

    private final PlanoManutencaoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public PlanoManutencaoServiceImpl(PlanoManutencaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public PlanoManutencao criarPlano(PlanoManutencaoDTO planoManutencaoDTO) {
        PlanoManutencao planoManutencao = modelMapper.map(planoManutencaoDTO, PlanoManutencao.class);
        planoManutencao.setId(UUID.randomUUID().toString());

        if (planoManutencao.getDataCriacao() == null) {
            planoManutencao.setDataCriacao(new Date());
        }
     
        return repository.save(planoManutencao);
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
    public PlanoManutencao atualizarPlano(String id, PlanoManutencaoDTO planoManutencaoDTO) {
        PlanoManutencao planoManutencao = modelMapper.map(planoManutencaoDTO, PlanoManutencao.class);
        if (repository.existsById(id)) {
            repository.save(planoManutencao);
        }
        return planoManutencao;
    }

    @Override
    public void deletarPlano(String id) {
        repository.deleteById(id);
    }

}