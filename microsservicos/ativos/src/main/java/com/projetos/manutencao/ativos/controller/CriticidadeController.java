package com.projetos.manutencao.ativos.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.manutencao.ativos.model.Criticidade;
import com.projetos.manutencao.ativos.service.CriticidadeService;

@RestController
@RequestMapping("/api/criticidades")
public class CriticidadeController {

    private final CriticidadeService service;

    public CriticidadeController(CriticidadeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Criticidade> create(@RequestBody Criticidade criticidade) {
        Criticidade created = service.create(criticidade);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Criticidade>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Criticidade> getById(@PathVariable String id) {
        Criticidade c = service.findById(id);
        return c == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Criticidade> update(@PathVariable String id, @RequestBody Criticidade criticidade) {
        Criticidade updated = service.update(id, criticidade);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
