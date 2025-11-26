package com.projetos.manutencao.ordem_manutencao.feign;

import com.projetos.manutencao.ordem_manutencao.DTO.FuncionarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "equipamento-service", url = "${services.equipamento.url}")
public interface EquipamentoClient {

    @GetMapping("/criticidade/{idEquipamento}")
    String getNivelCriticidadeEquipamento(@PathVariable String idEquipamento);

}
