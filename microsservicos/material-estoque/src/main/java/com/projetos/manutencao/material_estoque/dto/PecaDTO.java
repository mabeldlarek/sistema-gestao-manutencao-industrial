package com.projetos.manutencao.material_estoque.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class PecaDTO {
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

    public String getCodigoPeca() {
        return codigoPeca;
    }

    public void setCodigoPeca(String codigoPeca) {
        this.codigoPeca = codigoPeca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getNumeroCatalogo() {
        return numeroCatalogo;
    }

    public void setNumeroCatalogo(String numeroCatalogo) {
        this.numeroCatalogo = numeroCatalogo;
    }

    public Double getCustoUnitario() {
        return custoUnitario;
    }

    public void setCustoUnitario(Double custoUnitario) {
        this.custoUnitario = custoUnitario;
    }

    public Double getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(Double estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public Double getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Double estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public String getLocalizacaoAlmoxarifado() {
        return localizacaoAlmoxarifado;
    }

    public void setLocalizacaoAlmoxarifado(String localizacaoAlmoxarifado) {
        this.localizacaoAlmoxarifado = localizacaoAlmoxarifado;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}
