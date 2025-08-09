package com.projetos.manutencao.ordem_manutencao.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "planos_manutencao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoManutencao {

    @Id
    private String id; 

    private String codigo;
    private String nome;
    private String equipamentoID; 
    private String procedimentoID; 

    private String tipoManutencao;
    private String frequenciaTipo;
    private Number frequenciaValor;
    private String frequenciaUnidade;

    private Date proximaDataGeracao;
    private String status;
    private Date dataCriacao;
    private Date dataUltimaGeracao;
    private Boolean gerarAutomatico;

    private List<String> responsaveisPadraoID; 
    private String criticidadeMinimaGeracao;

}