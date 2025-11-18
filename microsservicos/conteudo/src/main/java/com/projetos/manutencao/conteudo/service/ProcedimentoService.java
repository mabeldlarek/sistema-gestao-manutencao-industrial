package com.projetos.manutencao.conteudo.service;
import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.conteudo.DTO.ProcedimentoDTO;
import com.projetos.manutencao.conteudo.model.Procedimento;

public interface ProcedimentoService {

    Procedimento create(ProcedimentoDTO procedimentoDTO);

    Optional<Procedimento> findById(String id);

    List<Procedimento> findAll();

    Procedimento update(String id, ProcedimentoDTO procedimentoDTO);

    void deleteById(String id);
}