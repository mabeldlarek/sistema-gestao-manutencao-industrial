package com.projetos.manutencao.conteudo.service.impl;
import java.util.List;
import java.util.NoSuchElementException;
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
        if (procedimentoDTO == null) {
            throw new IllegalArgumentException("Objeto ProcedimentoDTO não pode ser nulo.");
        }

        Procedimento procedimento = modelMapper.map(procedimentoDTO, Procedimento.class);
        
        if (procedimento.getId() == null || procedimento.getId().isBlank()) {
            procedimento.setId(UUID.randomUUID().toString());
        }

        return repository.save(procedimento);
    }

    @Override
    public Optional<Procedimento> findById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Procedimento não encontrado para atualização: " + id);
        }

        Procedimento procedimento = repository.findById(id).get();

        return Optional.of(procedimento);
    }

    @Override
    public List<Procedimento> findAll() {
        return repository.findAll();
    }

    @Override
    public Procedimento update(String id, ProcedimentoDTO procedimentoDTO) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (procedimentoDTO == null) {
            throw new IllegalArgumentException("Objeto ProcedimentoDTO não pode ser nulo.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Procedimento não encontrado para atualização: " + id);
        }

        Procedimento procedimento = modelMapper.map(procedimentoDTO, Procedimento.class);

        procedimento.setId(id);
        return repository.save(procedimento);
    }

    @Override
    public void deleteById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Procedimento não encontrado para atualização: " + id);
        }

        repository.deleteById(id);
    }
}