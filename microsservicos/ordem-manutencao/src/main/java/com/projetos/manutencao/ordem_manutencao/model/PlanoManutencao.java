package com.projetos.manutencao.ordem_manutencao.model;

import java.util.Date;
import java.util.List;

import com.projetos.manutencao.ordem_manutencao.enums.StatusPlano;
import com.projetos.manutencao.ordem_manutencao.enums.TipoFrequencia;
import com.projetos.manutencao.ordem_manutencao.enums.TipoManutencao;
import com.projetos.manutencao.ordem_manutencao.enums.UnidadeFrequencia;
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

    private TipoManutencao tipoManutencao;
    private TipoFrequencia frequenciaTipo;
    private Double frequenciaValor;
    private UnidadeFrequencia frequenciaUnidade;

    private StatusPlano status;
    private Date dataCriacao;
    private Date dataGeracaoAutomaticaOM;
    private Boolean gerarOMAutomatica;
    private Boolean gerarOMAutomaticaMedidor;

    private List<String> responsaveisPadraoID;
    private String codigoMedidor;


}