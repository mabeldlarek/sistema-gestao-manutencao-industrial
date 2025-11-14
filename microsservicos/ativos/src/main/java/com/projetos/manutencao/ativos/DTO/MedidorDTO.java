package com.projetos.manutencao.ativos.DTO;

import com.projetos.manutencao.ativos.enums.TipoMedidor;
import com.projetos.manutencao.ativos.enums.UnidadeMedida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedidorDTO {

    private String equipamentoId;

    private String nome;
    private TipoMedidor tipo;
    private UnidadeMedida unidade;

    private Double valorAtual;
    private Double valorMinimo;
    private Double valorMaximo;
}