package com.projetos.manutencao.ativos.controller;


import java.util.List;
import java.util.Map;

import com.projetos.manutencao.ativos.DTO.EquipamentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.manutencao.ativos.model.Equipamento;
import com.projetos.manutencao.ativos.service.EquipamentoService;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Equipamento> create(@RequestBody EquipamentoDTO equipamentoDTO) {
        Equipamento created = service.create(equipamentoDTO);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> getById(@PathVariable String id) {
        Equipamento e = service.findById(id);
        return e == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(e);
    }

    @GetMapping("/hierarquia/{codigo}")
    public ResponseEntity<List<Equipamento>> getArvore(@PathVariable String codigo) {
        return ResponseEntity.ok(service.getTreeEquipamentos(codigo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> update(@PathVariable String id, @RequestBody EquipamentoDTO equipamentoDTO) {
        Equipamento updated = service.update(id, equipamentoDTO);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/criticidade/{idEquipamento}")
    public ResponseEntity<String> getNivelCriticidadeEquipamento(@PathVariable String idEquipamento) {
        return ResponseEntity.ok(service.findNivelCriticidadeEquipamento(idEquipamento));
    }
}
