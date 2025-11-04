package com.projetos.manutencao.ativos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "criticidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Criticidade {
    @Id
    private String id;

    private Integer nivel;

    private String impactoProducao;
    private String impactoSeguranca;
    private String impactoAmbiental;

    private String custoReparo;
    private String frequenciaFalha;

    private String descricao;
}
