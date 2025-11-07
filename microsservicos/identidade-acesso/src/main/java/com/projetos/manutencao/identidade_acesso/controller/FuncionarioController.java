package com.projetos.manutencao.identidade_acesso.controller;

import java.util.List;

import com.projetos.manutencao.identidade_acesso.dto.auth.FuncionarioDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.manutencao.identidade_acesso.model.Funcionario;
import com.projetos.manutencao.identidade_acesso.service.FuncionarioService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable String id) {
        return service.findById(Long.getLong(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Funcionario> create(@Valid @RequestBody FuncionarioDTO funcionario) {
        Funcionario saved = service.save(funcionario);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(Long.getLong(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> put(@Valid @RequestBody FuncionarioDTO funcionario) {
        service.update(funcionario);
        return ResponseEntity.ok().build();
    }
}
