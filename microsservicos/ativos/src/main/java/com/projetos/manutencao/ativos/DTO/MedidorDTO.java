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

    @NotBlank(message = "O código é obrigatório.")
    private String codigo;

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoMedidor getTipo() {
        return tipo;
    }

    public void setTipo(TipoMedidor tipo) {
        this.tipo = tipo;
    }

    public UnidadeMedida getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeMedida unidade) {
        this.unidade = unidade;
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public Double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(Double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public Double getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(Double valorMaximo) {
        this.valorMaximo = valorMaximo;
    }
}