package com.projetos.manutencao.ativos.DTO;

import com.projetos.manutencao.ativos.enums.TipoMedidor;
import com.projetos.manutencao.ativos.enums.UnidadeMedida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedidorDTO {

    @NotBlank(message = "O nome do medidor é obrigatório.")
    private String nome;

    @NotNull(message = "O tipo do medidor é obrigatório.")
    private TipoMedidor tipo;

    @NotNull(message = "A unidade de medida é obrigatória.")
    private UnidadeMedida unidade;

    @NotNull(message = "O valor atual é obrigatório.")
    private Double valorAtual;

    @NotNull(message = "O valor mínimo é obrigatório.")
    private Double valorMinimo;

    @NotNull(message = "O valor máximo é obrigatório.")
    @Positive(message = "O valor máximo deve ser maior que zero.")
    private Double valorMaximo;
}