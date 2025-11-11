package com.projetos.manutencao.material_estoque.service;

import java.util.List;
import java.util.UUID;

import com.projetos.manutencao.material_estoque.dto.PecaDTO;
import com.projetos.manutencao.material_estoque.model.Peca;

public interface PecaService {
    Peca salvar(PecaDTO pecadto);
    List<Peca> listarTodas();
    Peca buscarPorId(UUID id);
    void deletar(UUID id);
    Peca update(UUID id, PecaDTO pecaDTO);

}
