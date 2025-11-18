package com.projetos.manutencao.conteudo.controller;
import java.util.List;
import java.util.Optional;

import com.projetos.manutencao.conteudo.DTO.PecaDTO;
import com.projetos.manutencao.conteudo.DTO.ProcedimentoDTO;
import com.projetos.manutencao.conteudo.DTO.ProcedimentoFormDataDTO;
import com.projetos.manutencao.conteudo.feign.PecaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.manutencao.conteudo.model.Procedimento;
import com.projetos.manutencao.conteudo.service.ProcedimentoService;

@RestController
@RequestMapping("/api/procedimentos")
public class ProcedimentoController {

    private final ProcedimentoService service;
    private final PecaClient pecaClient;

    public ProcedimentoController(ProcedimentoService service, PecaClient pecaClient) {
        this.service = service;
        this.pecaClient = pecaClient;
    }

    @GetMapping("/pecas")
    public List<PecaDTO> listar() {
        return pecaClient.listar();
    }

    @PostMapping
    public ResponseEntity<Procedimento> create(@RequestBody ProcedimentoDTO procedimentoDTO) {
        Procedimento criado = service.create(procedimentoDTO);
        return ResponseEntity.ok(criado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Procedimento> getById(@PathVariable String id) {
        Optional<Procedimento> opt = service.findById(id);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Procedimento>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Procedimento> update(@PathVariable String id, @RequestBody ProcedimentoDTO procedimentoDTO) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Procedimento atualizado = service.update(id, procedimentoDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}