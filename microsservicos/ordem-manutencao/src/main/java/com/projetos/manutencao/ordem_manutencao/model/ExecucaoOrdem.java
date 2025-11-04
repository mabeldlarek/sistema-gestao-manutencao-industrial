package com.projetos.manutencao.ordem_manutencao.model;

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
@Document(collection = "execucoes_ordem")
public class ExecucaoOrdem {

    @Id
    private String id; 
    private String ordemManutencaoID;
    private String executorID;
    private Date dataInicio;
    private Date dataFim;
    private Number tempoTrabalhado;
    private String descricaoTrabalhoExecutado;
    private String observacoesExecutor;
    private String statusExecucao; 
    private String assinaturaDigital;

    private List<ChecklistItem> checklistItens;
    private List<String> fotosAntes;  

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChecklistItem {
        private String descricao;
        private Boolean concluido;
    }
}