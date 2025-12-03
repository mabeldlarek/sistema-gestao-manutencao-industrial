package com.projetos.manutencao.identidade_acesso.controller;

import java.util.List;

import com.projetos.manutencao.identidade_acesso.dto.FuncionarioDTO;
import com.projetos.manutencao.identidade_acesso.service.UsuarioFuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UsuarioFuncionarioService usuarioFuncionarioService;

    public FuncionarioController(FuncionarioService service, UsuarioFuncionarioService usuarioFuncionarioService) {
        this.service = service;
        this.usuarioFuncionarioService = usuarioFuncionarioService;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Funcionario> getById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Funcionario> create(@Valid @RequestBody FuncionarioDTO funcionario) {
        Funcionario saved = service.save(funcionario);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        usuarioFuncionarioService.deleteByIdUsuarioVinculado(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> put(@PathVariable String id, @Valid @RequestBody FuncionarioDTO funcionario) {
        service.update(id, funcionario);
        return ResponseEntity.ok().build();
    }


}
