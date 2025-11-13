package com.projetos.manutencao.ativos.enums;

public enum Impacto {
    INSIGNIFICANTE(1, "Insignificante"),
    BAIXO(2, "Baixo"),
    MEDIO(3, "Médio"),
    ALTO(4, "Alto"),
    CATASTROFICO(5, "Catastrófico");

    private final int valor;
    private final String descricao;

    Impacto(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
