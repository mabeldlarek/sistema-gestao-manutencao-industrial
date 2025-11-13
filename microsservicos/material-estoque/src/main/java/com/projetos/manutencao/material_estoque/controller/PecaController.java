package com.projetos.manutencao.material_estoque.controller;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.projetos.manutencao.material_estoque.dto.PecaDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.manutencao.material_estoque.model.Peca;
import com.projetos.manutencao.material_estoque.service.PecaService;


@Slf4j
@RestController
@RequestMapping("/pecas")
public class PecaController {
    private final PecaService service;

    public PecaController(PecaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid PecaDTO peca) {
        service.salvar(peca);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Peca>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peca> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Peca> updateUsuario(@PathVariable UUID id, @Valid @RequestBody PecaDTO pecaDTO) {
        Peca atualizado = service.update(id, pecaDTO);
        return ResponseEntity.ok(atualizado);
    }
}
