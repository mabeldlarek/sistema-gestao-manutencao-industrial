package com.projetos.manutencao.ativos.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.*;
@Data
public class CriticidadeDTO {

    @Pattern(regexp = "^[A-D]$", message = "O nível deve ser uma letra de A a D")
    private String nivelCriticidadeCalculado;

    @NotNull
    @NotBlank(message = "O impacto na produção é obrigatório")
    private String impactoProducao;

    @NotNull
    @NotBlank(message = "A frequência de impacto na produção é obrigatoria")
    private String frequenciaImpactoProducao;

    @NotNull
    @NotBlank(message = "O impacto na segurança é obrigatório")
    private String impactoSeguranca;

    @NotBlank(message = "A frequência de impacto na segurança é obrigatoria")
    @NotNull
    private String frequenciaImpactoSeguranca;


    @NotBlank(message = "O impacto ambiental é obrigatório")
    @NotNull
    private String impactoAmbiental;

    @NotBlank(message = "A frequência de impacto ambiental é obrigatoria")
    @NotNull
    private String frequenciaImpactoAmbiental;

    @NotBlank(message = "O impacto no custo de reparo é obrigatório")
    @NotNull
    private String custoReparo;

    @NotBlank(message = "A frequência de impacto no custo de reparo é obrigatoria")
    @NotNull
    private String frequenciaCustoReparo;

    @NotBlank(message = "O impacto da falha é obrigatório")
    @NotNull
    private String impactoFalha;

    @NotBlank(message = "A frequência de falha é obrigatoria")
    @NotNull
    private String frequenciaFalha;

    public String getNivelCriticidadeCalculado() {
        return nivelCriticidadeCalculado;
    }

    public void setNivelCriticidadeCalculado(String nivelCriticidadeCalculado) {
        this.nivelCriticidadeCalculado = nivelCriticidadeCalculado;
    }

    public String getImpactoProducao() {
        return impactoProducao;
    }

    public void setImpactoProducao(String impactoProducao) {
        this.impactoProducao = impactoProducao;
    }

    public String getFrequenciaImpactoProducao() {
        return frequenciaImpactoProducao;
    }

    public void setFrequenciaImpactoProducao(String frequenciaImpactoProducao) {
        this.frequenciaImpactoProducao = frequenciaImpactoProducao;
    }

    public String getImpactoSeguranca() {
        return impactoSeguranca;
    }

    public void setImpactoSeguranca(String impactoSeguranca) {
        this.impactoSeguranca = impactoSeguranca;
    }

    public String getFrequenciaImpactoSeguranca() {
        return frequenciaImpactoSeguranca;
    }

    public void setFrequenciaImpactoSeguranca(String frequenciaImpactoSeguranca) {
        this.frequenciaImpactoSeguranca = frequenciaImpactoSeguranca;
    }

    public String getImpactoAmbiental() {
        return impactoAmbiental;
    }

    public void setImpactoAmbiental(String impactoAmbiental) {
        this.impactoAmbiental = impactoAmbiental;
    }

    public String getFrequenciaImpactoAmbiental() {
        return frequenciaImpactoAmbiental;
    }

    public void setFrequenciaImpactoAmbiental(String frequenciaImpactoAmbiental) {
        this.frequenciaImpactoAmbiental = frequenciaImpactoAmbiental;
    }

    public String getCustoReparo() {
        return custoReparo;
    }

    public void setCustoReparo(String custoReparo) {
        this.custoReparo = custoReparo;
    }

    public String getFrequenciaCustoReparo() {
        return frequenciaCustoReparo;
    }

    public void setFrequenciaCustoReparo(String frequenciaCustoReparo) {
        this.frequenciaCustoReparo = frequenciaCustoReparo;
    }

    public String getImpactoFalha() {
        return impactoFalha;
    }

    public void setImpactoFalha(String impactoFalha) {
        this.impactoFalha = impactoFalha;
    }

    public String getFrequenciaFalha() {
        return frequenciaFalha;
    }

    public void setFrequenciaFalha(String frequenciaFalha) {
        this.frequenciaFalha = frequenciaFalha;
    }
}


