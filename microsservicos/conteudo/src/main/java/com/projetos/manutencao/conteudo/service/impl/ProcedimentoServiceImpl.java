package com.projetos.manutencao.conteudo.service.impl;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.projetos.manutencao.conteudo.DTO.ProcedimentoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.conteudo.model.Procedimento;
import com.projetos.manutencao.conteudo.repository.ProcedimentoRepository;
import com.projetos.manutencao.conteudo.service.ProcedimentoService;

@Service
public class ProcedimentoServiceImpl implements ProcedimentoService {

    private final ProcedimentoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public ProcedimentoServiceImpl(ProcedimentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Procedimento create(ProcedimentoDTO procedimentoDTO) {
        Procedimento procedimento = modelMapper.map(procedimentoDTO, Procedimento.class);
        procedimento.setId(UUID.randomUUID().toString());
        return repository.save(procedimento);
    }

    @Override
    public Optional<Procedimento> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Procedimento> findAll() {
        return repository.findAll();
    }

    @Override
    public Procedimento update(String id, ProcedimentoDTO procedimentoDTO) {
        Procedimento procedimento = modelMapper.map(procedimentoDTO, Procedimento.class);
        procedimento.setId(id);
        return repository.save(procedimento);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}