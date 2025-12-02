package com.projetos.manutencao.material_estoque.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.projetos.manutencao.material_estoque.dto.PecaConsumoDTO;
import com.projetos.manutencao.material_estoque.model.Peca;
import com.projetos.manutencao.material_estoque.repository.PecaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.material_estoque.model.PecaConsumo;
import com.projetos.manutencao.material_estoque.repository.PecaConsumoRepository;
import com.projetos.manutencao.material_estoque.service.PecaConsumoService;

@Service
public class PecaConsumoServiceImpl implements PecaConsumoService {
    private final PecaConsumoRepository repository;
    private final PecaRepository pecaRepository;
    @Autowired
    private ModelMapper modelMapper;

    public PecaConsumoServiceImpl(PecaConsumoRepository repository, PecaRepository pecaRepository) {
        this.repository = repository;
        this.pecaRepository = pecaRepository;
    }

    public PecaConsumo salvar(PecaConsumoDTO consumoDTO) {
        if (consumoDTO == null) {
            throw new IllegalArgumentException("Objeto ConsumoDTO não pode ser nulo.");
        }

        PecaConsumo pecaConsumo = modelMapper.map(consumoDTO, PecaConsumo.class);

        modificarEstoqueDisponivel(consumoDTO);
        return repository.save(pecaConsumo);
    }

    public List<PecaConsumo> listarTodos() {
        return repository.findAll();
    }

    public PecaConsumo buscarPorId(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Equipamento não encontrado para atualização: " + id);
        }

        repository.deleteById(id);
    }

    public PecaConsumo update(UUID id, PecaConsumoDTO consumoDTO) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }

        if (consumoDTO == null) {
            throw new IllegalArgumentException("Objeto ConsumoDTO não pode ser nulo.");
        }

        PecaConsumo existente = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Consumo de Peça não encontrada"));

        modelMapper.map(consumoDTO, existente);
        return repository.save(existente);
    }

    private void modificarEstoqueDisponivel(PecaConsumoDTO pecaConsumoDTO){
        if (pecaConsumoDTO == null) {
            throw new IllegalArgumentException("Objeto PecaConsumoDTO não pode ser nulo.");
        }

        Peca peca = pecaRepository.findById(pecaConsumoDTO.getPecaID())
                .orElseThrow(() -> new EntityNotFoundException("Peça não encontrada"));

        double estoquePrevisto = peca.getEstoqueAtual() - pecaConsumoDTO.getQuantidade();

        if (estoquePrevisto < peca.getEstoqueMinimo()) {
            throw new IllegalArgumentException("Estoque mínimo insuficiente");
        }

        peca.setEstoqueAtual(estoquePrevisto);
    }

}