package com.projetos.manutencao.material_estoque.service;

import java.util.List;
import java.util.UUID;

import com.projetos.manutencao.material_estoque.dto.PecaConsumoDTO;
import com.projetos.manutencao.material_estoque.model.PecaConsumo;

public interface PecaConsumoService {
    PecaConsumo salvar(PecaConsumoDTO consumo);
    List<PecaConsumo> listarTodos();
    PecaConsumo buscarPorId(UUID id);
    void deletar(UUID id);
    PecaConsumo update(UUID id, PecaConsumoDTO consumo);

}