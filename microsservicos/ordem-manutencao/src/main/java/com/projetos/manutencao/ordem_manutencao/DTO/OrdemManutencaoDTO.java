package com.projetos.manutencao.ordem_manutencao.DTO;

import com.projetos.manutencao.ordem_manutencao.enums.Prioridade;
import com.projetos.manutencao.ordem_manutencao.enums.StatusOrdem;
import com.projetos.manutencao.ordem_manutencao.enums.TipoManutencao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemManutencaoDTO {

    @NotBlank(message = "O número da OS é obrigatório.")
    private String numeroOS;

    @NotBlank(message = "O ID do equipamento é obrigatório.")
    private String equipamentoID;

    private String planoManutencaoID;

    @NotBlank(message = "A descrição do problema é obrigatória.")
    private String descricaoProblema;

    @NotNull(message = "O tipo de manutenção é obrigatório.")
    private TipoManutencao tipoManutencao;

    @NotNull(message = "O status é obrigatório.")
    private StatusOrdem status;

    @NotNull(message = "A prioridade é obrigatória.")
    private Prioridade prioridade;

    @NotNull(message = "A data de abertura é obrigatória.")
    private Date dataAbertura;

    private Date dataFechamento;

    @NotBlank(message = "O ID do solicitante é obrigatório.")
    private String solicitanteID;

    private String responsavelID;
    private String procedimentoID;

    @Size(max = 1000)
    private String observacoes;

    private Double custoEstimado;
    private Double custoReal;

    private Long tempoParadaEstimado;
    private Long tempoParadaReal;

    private String modoFalhaID;
    private String causaRaizID;
}