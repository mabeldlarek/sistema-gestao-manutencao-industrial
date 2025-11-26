package com.projetos.manutencao.ordem_manutencao.controller;

import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.ordem_manutencao.DTO.FuncionarioDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.OrdemManutencaoDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.UpdateStatusOrdemDTO;
import com.projetos.manutencao.ordem_manutencao.enums.StatusOrdem;
import com.projetos.manutencao.ordem_manutencao.feign.FuncionarioClient;
import com.projetos.manutencao.ordem_manutencao.model.ExecucaoOrdem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;
import com.projetos.manutencao.ordem_manutencao.service.OrdemManutencaoService;

@RestController
@RequestMapping("/api/os")
public class OrdemManutencaoController {

    private final OrdemManutencaoService service;
    private final FuncionarioClient funcionarioClient;

    public OrdemManutencaoController(OrdemManutencaoService service, FuncionarioClient funcionarioClient) {
        this.service = service;
        this.funcionarioClient = funcionarioClient;
    }

    @GetMapping("/funcionarios")
    public List<FuncionarioDTO> buscarFuncionarios() {
        return funcionarioClient.listar();
    }

    @PostMapping
    public ResponseEntity<OrdemManutencao> create(@RequestBody OrdemManutencaoDTO ordem) {
        OrdemManutencao created = service.create(ordem);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<OrdemManutencao>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemManutencao> getById(@PathVariable String id) {
        Optional<OrdemManutencao> opt = service.findById(id);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemManutencao> update(@PathVariable String id, @RequestBody OrdemManutencaoDTO ordem) {
        OrdemManutencao updated = service.update(id, ordem);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable String id, @RequestBody UpdateStatusOrdemDTO statusOrdemDTO) {
        service.updateStatus(id, statusOrdemDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}