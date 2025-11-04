package com.projetos.manutencao.identidade_acesso.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "funcionarios")
@Data
public class Funcionario {

    @Id
    private String id;
    private String matricula;
    private String nome;
    private String cargo;
    private String equipe;
    private List<String> especialidades;
    private String status;

    private UUID usuarioId;

}
