package com.projetos.manutencao.identidade_acesso.controller;

import java.util.UUID;

import com.projetos.manutencao.identidade_acesso.dto.auth.UsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.manutencao.identidade_acesso.model.Usuario;
import com.projetos.manutencao.identidade_acesso.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("usuarios")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<Object> getListaUsuarios(HttpServletRequest request) {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);

    }

    @GetMapping("usuarios/{id}")
    public ResponseEntity<Object> getUsuarioId(HttpServletRequest request, @PathVariable String id) {
        return new ResponseEntity<>(usuarioService.findById(UUID.fromString(id)), HttpStatus.OK);
    }

    @PostMapping("usuarios")
    public ResponseEntity<Object> createUsuario(HttpServletRequest request, @Valid @RequestBody UsuarioDTO usuario) {
        usuarioService.save(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("usuarios/{id}")
    public ResponseEntity<Object> updateUsuario(HttpServletRequest request, @Valid @RequestBody UsuarioDTO usuario) {
        usuarioService.update(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("usuarios/{id}")
    public ResponseEntity<Object> deleteUsuario(HttpServletRequest request, @PathVariable String id) {
        usuarioService.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
