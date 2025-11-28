package com.projetos.manutencao.ativos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "criticidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Criticidade {
    @Id
    @Indexed(unique = true)
    private String id;

    private String nivel;

    private String impactoProducao;
    private String frequenciaImpactoProducao;

    private String impactoSeguranca;
    private String frequenciaImpactoSeguranca;

    private String frequenciaImpactoAmbiental;
    private String impactoAmbiental;

    private String frequenciaCustoReparo;
    private String custoReparo;

    private String frequenciaFalha;
    private String impactoFalha;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
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

    public String getFrequenciaImpactoAmbiental() {
        return frequenciaImpactoAmbiental;
    }

    public void setFrequenciaImpactoAmbiental(String frequenciaImpactoAmbiental) {
        this.frequenciaImpactoAmbiental = frequenciaImpactoAmbiental;
    }

    public String getImpactoAmbiental() {
        return impactoAmbiental;
    }

    public void setImpactoAmbiental(String impactoAmbiental) {
        this.impactoAmbiental = impactoAmbiental;
    }

    public String getFrequenciaCustoReparo() {
        return frequenciaCustoReparo;
    }

    public void setFrequenciaCustoReparo(String frequenciaCustoReparo) {
        this.frequenciaCustoReparo = frequenciaCustoReparo;
    }

    public String getCustoReparo() {
        return custoReparo;
    }

    public void setCustoReparo(String custoReparo) {
        this.custoReparo = custoReparo;
    }

    public String getFrequenciaFalha() {
        return frequenciaFalha;
    }

    public void setFrequenciaFalha(String frequenciaFalha) {
        this.frequenciaFalha = frequenciaFalha;
    }

    public String getImpactoFalha() {
        return impactoFalha;
    }

    public void setImpactoFalha(String impactoFalha) {
        this.impactoFalha = impactoFalha;
    }
}
