package com.projetos.manutencao.ativos.controller;

import com.projetos.manutencao.ativos.DTO.MedidorDTO;
import com.projetos.manutencao.ativos.model.Medidor;
import com.projetos.manutencao.ativos.service.MedidorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medidores")
public class MedidorController {

    private final MedidorService service;

    public MedidorController(MedidorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Medidor> create(@PathVariable String id, @RequestBody MedidorDTO medidorDTO) {
        Medidor criado = service.create(id, medidorDTO);

        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping("/equipamento/{equipamentoId}")
    public ResponseEntity<List<Medidor>> findByEquipamento(@PathVariable String equipamentoId) {
        return ResponseEntity.ok(service.findByEquipamento(equipamentoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medidor> findById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}