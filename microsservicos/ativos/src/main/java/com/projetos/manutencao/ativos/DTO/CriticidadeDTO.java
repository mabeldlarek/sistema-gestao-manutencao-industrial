package com.projetos.manutencao.ativos.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.*;
@Data
public class CriticidadeDTO {

    @Pattern(regexp = "^[A-D]$", message = "O nível deve ser uma letra de A a D")
    private String nivelCriticidadeCalculado;

    @NotNull
    private String impactoProducao;

    @NotNull
    private String frequenciaImpactoProducao;

    @NotNull
    private String impactoSeguranca;

    @NotNull
    private String frequenciaImpactoSeguranca;

    @NotNull
    private String impactoAmbiental;

    @NotNull
    private String frequenciaImpactoAmbiental;

    @NotNull
    private String custoReparo;

    @NotNull
    private String frequenciaCustoReparo;

    @NotNull
    private String impactoFalha;

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


