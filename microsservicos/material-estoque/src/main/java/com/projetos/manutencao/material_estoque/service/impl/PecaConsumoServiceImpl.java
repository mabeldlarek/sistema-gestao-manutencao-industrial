package com.projetos.manutencao.material_estoque.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.projetos.manutencao.material_estoque.model.PecaConsumo;
import com.projetos.manutencao.material_estoque.repository.PecaConsumoRepository;
import com.projetos.manutencao.material_estoque.service.PecaConsumoService;

@Service
public class PecaConsumoServiceImpl implements PecaConsumoService {
    private final PecaConsumoRepository repository;

    public PecaConsumoServiceImpl(PecaConsumoRepository repository) {
        this.repository = repository;
    }

    public PecaConsumo salvar(PecaConsumo consumo) {
        return repository.save(consumo);
    }

    public List<PecaConsumo> listarTodos() {
        return repository.findAll();
    }

    public PecaConsumo buscarPorId(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(UUID id) {
        repository.deleteById(id);
    }

    public PecaConsumo update(PecaConsumo consumo) {
        return repository.save(consumo);
    }

}