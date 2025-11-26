package com.projetos.manutencao.ordem_manutencao.feign;

import com.projetos.manutencao.ordem_manutencao.DTO.FuncionarioDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.MedidorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "funcionario-service", url = "${services.funcionario.url}")
public interface FuncionarioClient {

    @GetMapping("/funcionarios")
    List<FuncionarioDTO> listar();

}