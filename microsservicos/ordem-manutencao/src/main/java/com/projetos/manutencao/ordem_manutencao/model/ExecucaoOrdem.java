package com.projetos.manutencao.ordem_manutencao.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.projetos.manutencao.ordem_manutencao.enums.StatusExecucao;
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
    private List<PeriodoTrabalho> periodosDeTrabalho;
    private String descricaoTrabalhoExecutado;
    private String observacoesExecutor;
    private StatusExecucao statusExecucao;
    private String assinaturaDigital;

    private List<ChecklistItem> checklistItens;
    private List<String> fotosAntes;
    private List<String> fotosDepois;

}