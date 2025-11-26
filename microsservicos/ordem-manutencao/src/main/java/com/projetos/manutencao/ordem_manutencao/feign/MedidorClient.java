package com.projetos.manutencao.ordem_manutencao.feign;

import com.projetos.manutencao.ordem_manutencao.DTO.MedidorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "medidor-service", url = "${services.medidor.url}")
public interface MedidorClient {

    @GetMapping("/{id}")
    MedidorDTO buscar(@PathVariable String id);

    @GetMapping
    List<MedidorDTO> listar();

}