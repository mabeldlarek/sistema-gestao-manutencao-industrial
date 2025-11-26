package com.projetos.manutencao.ordem_manutencao.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedidorDTO {

    private String codigo;
    private Double valorAtual;
    private Double valorMinimo;
    private Double valorMaximo;
}