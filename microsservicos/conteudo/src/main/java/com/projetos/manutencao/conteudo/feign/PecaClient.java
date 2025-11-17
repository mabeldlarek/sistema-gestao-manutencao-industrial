package com.projetos.manutencao.conteudo.feign;

import com.projetos.manutencao.conteudo.DTO.PecaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "peca-service", url = "${services.pecas.url}")
public interface PecaClient {

    @GetMapping("/{id}")
    PecaDTO buscar(@PathVariable UUID id);

    @GetMapping
    List<PecaDTO> listar();

}