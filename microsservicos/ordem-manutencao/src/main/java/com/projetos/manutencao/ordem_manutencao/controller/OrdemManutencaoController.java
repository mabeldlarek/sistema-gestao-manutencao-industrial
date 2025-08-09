package com.projetos.manutencao.ordem_manutencao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.manutencao.ordem_manutencao.model.OrdemManutencao;
import com.projetos.manutencao.ordem_manutencao.service.OrdemManutencaoService;

@RestController
@RequestMapping("/api/os")
public class OrdemManutencaoController {

    private final OrdemManutencaoService service;

    public OrdemManutencaoController(OrdemManutencaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrdemManutencao> create(@RequestBody OrdemManutencao ordem) {
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
    public ResponseEntity<OrdemManutencao> update(@PathVariable String id, @RequestBody OrdemManutencao ordem) {
        OrdemManutencao updated = service.update(id, ordem);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}