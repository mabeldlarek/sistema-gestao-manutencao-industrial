package com.projetos.manutencao.material_estoque.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.projetos.manutencao.material_estoque.model.Peca;
import com.projetos.manutencao.material_estoque.repository.PecaRepository;
import com.projetos.manutencao.material_estoque.service.PecaService;

@Service
public class PecaServiceImpl implements PecaService {
    private final PecaRepository repository;

    public PecaServiceImpl(PecaRepository repository) {
        this.repository = repository;
    }

    public Peca salvar(Peca peca) {
        return repository.save(peca);
    }

    public List<Peca> listarTodas() {
        return repository.findAll();
    }

    public Peca buscarPorId(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(UUID id) {
        repository.deleteById(id);
    }

    public Peca update(Peca peca) {
        return repository.save(peca);
    }

}
