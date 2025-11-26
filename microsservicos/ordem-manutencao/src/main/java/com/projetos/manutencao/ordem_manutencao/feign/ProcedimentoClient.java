package com.projetos.manutencao.ordem_manutencao.feign;

import com.projetos.manutencao.ordem_manutencao.DTO.MedidorDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.ProcedimentoCheklistDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "conteudo-service", url = "${services.conteudo.url}")
public interface ProcedimentoClient {

    @GetMapping("/{id}")
    ProcedimentoCheklistDTO buscar(@PathVariable String id);

}
