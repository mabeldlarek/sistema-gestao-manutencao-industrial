package com.projetos.manutencao.conteudo.model;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "procedimentos")
public class Procedimento {

    @Id
    private String id; 

    private String codigo;
    private String titulo;
    private String descricao;

    private String tipoManutencao;

    private List<String> passosChecklist;

    private List<String> ferramentasNecessarias;

    private List<String> idPecasNecessarias;

    private Number tempoEstimado;

    private List<String> riscosAssociados;

    private List<String> EPIsRequeridos;

    private List<String> documentosAnexados; 

    private Date dataCriacao;

    private Date dataUltimaRevisao;

    private String revisadoPor; 
}