package com.projetos.manutencao.conteudo.service;
import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.conteudo.model.Procedimento;

public interface ProcedimentoService {

    Procedimento create(Procedimento procedimento);

    Optional<Procedimento> findById(String id);

    List<Procedimento> findAll();

    Procedimento update(Procedimento procedimento);

    void deleteById(String id);
}