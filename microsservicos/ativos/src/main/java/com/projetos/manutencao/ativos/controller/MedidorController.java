package com.projetos.manutencao.ativos.controller;

import com.projetos.manutencao.ativos.DTO.MedidorDTO;
import com.projetos.manutencao.ativos.model.Medidor;
import com.projetos.manutencao.ativos.service.MedidorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/medidores")
public class MedidorController {

    private final MedidorService service;

    public MedidorController(MedidorService service) {
        this.service = service;
    }

    @PostMapping("/equipamento/{equipamentoId}")
    public ResponseEntity<Medidor> create(@PathVariable String equipamentoId, @RequestBody MedidorDTO medidorDTO) {
        Medidor criado = service.create(equipamentoId, medidorDTO);
        URI uri = URI.create("/medidor/" + criado.getId());
        return ResponseEntity.created(uri).body(criado);
    }

    @GetMapping("/equipamento/{equipamentoId}")
    public ResponseEntity<List<Medidor>> findByEquipamento(@PathVariable String equipamentoId) {
        return ResponseEntity.ok(service.findByEquipamento(equipamentoId));
    }

    @GetMapping("/{id}")
    public Medidor findById(@PathVariable String id) {
        Medidor medidor = service.findById(id);
        return ResponseEntity.ok(medidor).getBody();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}