package com.projetos.manutencao.ativos.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medidor {
    private String nome;
    private String unidade;
    private Double valorAtual;
    private Double valorMinimo;
    private Double valorMaximo;
}