package com.projetos.manutencao.conteudo.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedimentoDTO {
    @NotBlank(message = "O código é obrigatório.")
    private String codigo;

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotBlank(message = "O tipo de manutenção é obrigatório.")
    private String tipoManutencao;

    @NotEmpty(message = "É necessário informar ao menos 1 passo do checklist.")
    private List<@NotBlank String> passosChecklist;

    @NotEmpty(message = "É necessário informar as ferramentas necessárias.")
    private List<@NotBlank String> ferramentasNecessarias;

    @NotEmpty(message = "É necessário informar ao menos uma peça.")
    private List<@NotBlank String> pecasNecessarias;

    @NotNull(message = "O tempo estimado é obrigatório.")
    @Positive(message = "O tempo estimado deve ser maior que zero.")
    private Number tempoEstimado;

    @NotEmpty(message = "É necessário informar os riscos associados.")
    private List<@NotBlank String> riscosAssociados;

    @NotEmpty(message = "É necessário informar os EPIs requeridos.")
    private List<@NotBlank String> EPIsRequeridos;

    private List<@NotBlank String> documentosAnexados;

    private Date dataCriacao;

    @PastOrPresent(message = "A data de última revisão não pode ser futura.")
    private Date dataUltimaRevisao;

    @NotBlank(message = "O campo revisadoPor não pode estar em branco.")
    private String revisadoPor;
}
