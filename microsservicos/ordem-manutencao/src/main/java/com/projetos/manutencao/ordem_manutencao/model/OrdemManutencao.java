package com.projetos.manutencao.ordem_manutencao.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "ordens_manutencao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemManutencao {
    @Id
    private String id; 

    @Indexed(unique = true)
    private String numeroOS;

    private String equipamentoID; 
    private String descricaoProblema;

    private String tipoManutencao;
    private String status;
    private String prioridade;

    private Date dataAbertura;
    private Date dataFechamento;

    private String solicitanteID; 
    private String responsavelID; 
    private String procedimentoID; 

    private String observacoes;

    private Double custoEstimado;
    private Double custoReal;

    private Long tempoParadaEstimado; 
    private Long tempoParadaReal;     

    private String modoFalhaID; 
    private String causaRaizID; 
}