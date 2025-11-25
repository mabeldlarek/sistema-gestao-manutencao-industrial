package com.projetos.manutencao.ordem_manutencao.controller;

import java.util.List;

import com.projetos.manutencao.ordem_manutencao.DTO.MedidorDTO;
import com.projetos.manutencao.ordem_manutencao.DTO.PlanoManutencaoDTO;
//import com.projetos.manutencao.ordem_manutencao.feign.FuncionarioClient;
import com.projetos.manutencao.ordem_manutencao.feign.MedidorClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.manutencao.ordem_manutencao.model.PlanoManutencao;
import com.projetos.manutencao.ordem_manutencao.service.PlanoManutencaoService;

@RestController
@RequestMapping("/planos")
public class PlanoManutencaoController {

    private final PlanoManutencaoService service;
    private final MedidorClient medidorClient;
    //private final FuncionarioClient funcionarioClient;

    public PlanoManutencaoController(PlanoManutencaoService service, MedidorClient medidorClient) {
        this.service = service;
        this.medidorClient = medidorClient;
    }

    @GetMapping("/{id}/medidores")
    public MedidorDTO listarMedidor(@PathVariable String id) {
        return medidorClient.buscar(id);
    }

    @PostMapping
    public ResponseEntity<PlanoManutencao> criar(@RequestBody PlanoManutencaoDTO plano) {
        PlanoManutencao criado = service.criarPlano(plano);
        return ResponseEntity.ok(criado);
    }

    /*@GetMapping("/funcionario")
    public List<FuncionarioDTO> listar() {
        //return funcionarioClient.listar();
    }*/

    @GetMapping
    public ResponseEntity<List<PlanoManutencao>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoManutencao> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoManutencao> atualizar(@PathVariable String id, @RequestBody PlanoManutencaoDTO plano) {
        return ResponseEntity.ok(service.atualizarPlano(id, plano));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletarPlano(id);
        return ResponseEntity.noContent().build();
    }


}