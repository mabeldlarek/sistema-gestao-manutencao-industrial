package com.projetos.manutencao.ordem_manutencao.DTO;

import com.projetos.manutencao.ordem_manutencao.enums.StatusPlano;
import com.projetos.manutencao.ordem_manutencao.enums.TipoFrequencia;
import com.projetos.manutencao.ordem_manutencao.enums.TipoManutencao;
import com.projetos.manutencao.ordem_manutencao.enums.UnidadeFrequencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoManutencaoDTO {

    @NotBlank(message = "O código do plano é obrigatório.")
    private String codigo;

    @NotBlank(message = "O nome do plano é obrigatório.")
    private String nome;

    @NotBlank(message = "O ID do equipamento é obrigatório.")
    private String equipamentoID;

    private String procedimentoID;

    @NotNull(message = "O tipo de manutenção é obrigatório.")
    private TipoManutencao tipoManutencao;

    @NotBlank(message = "O tipo de frequência é obrigatório.")
    private TipoFrequencia frequenciaTipo;

    private Double frequenciaValor;

    @NotBlank(message = "A unidade de frequência é obrigatória.")
    private UnidadeFrequencia frequenciaUnidade;

    @NotNull(message = "O status do plano é obrigatório.")
    private StatusPlano status;

    private Date dataCriacao;
    private Date dataGeracaoAutomaticaOM;
    private Boolean gerarOMAutomatica;
    private Boolean gerarOMAutomaticaMedidor;
    private String codigoMedidor;

    private List<String> responsaveisPadraoID;

}
