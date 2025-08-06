package com.projetos.manutencao.material_estoque.service;

import java.util.List;
import java.util.UUID;

import com.projetos.manutencao.material_estoque.model.Peca;

public interface PecaService {
    Peca salvar(Peca peca);
    List<Peca> listarTodas();
    Peca buscarPorId(UUID id);
    void deletar(UUID id);
    Peca update(Peca consumo);

}
