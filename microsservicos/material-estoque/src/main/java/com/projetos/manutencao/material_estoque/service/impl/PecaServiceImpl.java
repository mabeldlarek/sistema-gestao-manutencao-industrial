package com.projetos.manutencao.material_estoque.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.projetos.manutencao.material_estoque.dto.PecaDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.manutencao.material_estoque.model.Peca;
import com.projetos.manutencao.material_estoque.repository.PecaRepository;
import com.projetos.manutencao.material_estoque.service.PecaService;

@Service
public class PecaServiceImpl implements PecaService {
    private final PecaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public PecaServiceImpl(PecaRepository repository) {
        this.repository = repository;
    }

    public Peca salvar(PecaDTO pecaDto) {
        Peca peca = modelMapper.map(pecaDto, Peca.class);
        System.out.println(peca.toString());
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

    public Peca update(UUID id, PecaDTO pecaDTO) {
        Peca existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Peça não encontrada"));

        modelMapper.map(pecaDTO, existente);
        return repository.save(existente);
    }


}
