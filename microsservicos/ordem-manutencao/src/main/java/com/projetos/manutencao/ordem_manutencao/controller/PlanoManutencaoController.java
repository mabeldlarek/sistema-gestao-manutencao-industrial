package com.projetos.manutencao.ordem_manutencao.controller;

import java.util.List;

import com.projetos.manutencao.ordem_manutencao.DTO.MedidorDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.PlanoManutencaoDTO;
//import com.projetos.manutencao.ordem_manutencao.feign.FuncionarioClient;
import com.projetos.manutencao.ordem_manutencao.DTO.UpdateStatusOrdemDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.UpdateStatusPlanoDTO;
import com.projetos.manutencao.ordem_manutencao.feign.MedidorClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;
import com.projetos.manutencao.ordem_manutencao.service.PlanoManutencaoService;

@RestController
@RequestMapping("/planos")
public class PlanoManutencaoController {

    private final PlanoManutencaoService service;
    private final MedidorClient medidorClient;
    //private final FuncionarioClient funcionarioClient;

    public PlanoManutencaoController(PlanoManutencaoService service, MedidorClient medidorClient) {
        this.service = service;
        this.medidorClient = medidorClient;
    }

    @GetMapping("/{id}/medidores")
    public MedidorDTO listarMedidor(@PathVariable String id) {
        return medidorClient.buscar(id);
    }

    @PostMapping
    public ResponseEntity<PlanoManutencao> criar(@RequestBody PlanoManutencaoDTO plano) {
        PlanoManutencao criado = service.criarPlano(plano);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public ResponseEntity<List<PlanoManutencao>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoManutencao> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoManutencao> atualizar(@PathVariable String id, @RequestBody PlanoManutencaoDTO plano) {
        return ResponseEntity.ok(service.atualizarPlano(id, plano));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable String id, @RequestBody UpdateStatusPlanoDTO statusPlanoDTO) {
        service.updateStatus(id, statusPlanoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletarPlano(id);
        return ResponseEntity.noContent().build();
    }




}