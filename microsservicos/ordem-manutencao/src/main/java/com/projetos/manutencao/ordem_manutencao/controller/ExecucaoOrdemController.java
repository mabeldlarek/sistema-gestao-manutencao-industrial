package com.projetos.manutencao.ordem_manutencao.controller;

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

import com.projetos.manutencao.ordem_manutencao.model.ExecucaoOrdem;
import com.projetos.manutencao.ordem_manutencao.service.ExecucaoOrdemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/execucoes")
@RequiredArgsConstructor
public class ExecucaoOrdemController {

    private final ExecucaoOrdemService service;

    @PostMapping
    public ResponseEntity<ExecucaoOrdem> criar(@RequestBody ExecucaoOrdem execucao) {
        return ResponseEntity.ok(service.criarExecucao(execucao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExecucaoOrdem> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ExecucaoOrdem>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExecucaoOrdem> atualizar(@PathVariable String id, @RequestBody ExecucaoOrdem execucao) {
        return ResponseEntity.ok(service.atualizarExecucao(id, execucao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletarExecucao(id);
        return ResponseEntity.noContent().build();
    }
}