package com.projetos.manutencao.conteudo.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projetos.manutencao.conteudo.model.Procedimento;
import com.projetos.manutencao.conteudo.repository.ProcedimentoRepository;
import com.projetos.manutencao.conteudo.service.ProcedimentoService;

@Service
public class ProcedimentoServiceImpl implements ProcedimentoService {

    private final ProcedimentoRepository repository;

    public ProcedimentoServiceImpl(ProcedimentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Procedimento create(Procedimento procedimento) {
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
    public Procedimento update(Procedimento procedimento) {
        return repository.save(procedimento);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}