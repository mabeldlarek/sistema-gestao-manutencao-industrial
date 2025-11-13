package com.projetos.manutencao.material_estoque.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class PecaDTO {
    @NotBlank(message = "O código da peça é obrigatório.")
    @Size(max = 50, message = "O código da peça deve ter no máximo 50 caracteres.")
    private String codigoPeca;

    @NotBlank(message = "O nome da peça é obrigatório.")
    @Size(max = 100, message = "O nome da peça deve ter no máximo 100 caracteres.")
    private String nome;

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.")
    private String descricao;

    @NotBlank(message = "O fabricante é obrigatório.")
    @Size(max = 100, message = "O fabricante deve ter no máximo 100 caracteres.")
    private String fabricante;

    @NotBlank(message = "O número de catálogo é obrigatório.")
    @Size(max = 50, message = "O número de catálogo deve ter no máximo 50 caracteres.")
    private String numeroCatalogo;

    @PositiveOrZero(message = "O custo unitário deve ser maior ou igual a zero.")
    private Double custoUnitario;

    @PositiveOrZero(message = "O estoque atual deve ser maior ou igual a zero.")
    private Double estoqueAtual;

    @PositiveOrZero(message = "O estoque mínimo deve ser maior ou igual a zero.")
    private Double estoqueMinimo;

    @NotBlank(message = "A localização é obrigatória.")
    @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres.")
    private String localizacaoAlmoxarifado;

    @NotBlank(message = "A unidade de medida é obrigatória.")
    @Size(max = 20, message = "A unidade de medida deve ter no máximo 20 caracteres.")
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
