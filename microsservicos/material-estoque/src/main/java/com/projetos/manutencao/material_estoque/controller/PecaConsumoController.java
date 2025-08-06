package com.projetos.manutencao.material_estoque.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.manutencao.material_estoque.model.PecaConsumo;
import com.projetos.manutencao.material_estoque.service.PecaConsumoService;

@RestController
@RequestMapping("/pecas-consumo")
public class PecaConsumoController {
    private final PecaConsumoService service;

    public PecaConsumoController(PecaConsumoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PecaConsumo> salvar(@RequestBody PecaConsumo consumo) {
        return ResponseEntity.ok(service.salvar(consumo));
    }

    @GetMapping
    public ResponseEntity<List<PecaConsumo>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PecaConsumo> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PecaConsumo> updateUsuario(@PathVariable UUID id, @RequestBody PecaConsumo consumo) {
        consumo.setId(id);
        PecaConsumo atualizado = service.salvar(consumo);
        return ResponseEntity.ok(atualizado);
    }
}