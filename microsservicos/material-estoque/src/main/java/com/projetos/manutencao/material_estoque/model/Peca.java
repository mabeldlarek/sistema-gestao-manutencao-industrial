package com.projetos.manutencao.material_estoque.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Peca {
    @Id
    private UUID id;
    private String codigoPeca;
    private String nome;
    private String descricao;
    private String fabricante;
    private String numeroCatalogo;
    private Double custoUnitario;
    private Double estoqueAtual;
    private Double estoqueMinimo;
    private String localizacaoAlmoxarifado;
    private String unidadeMedida;
}