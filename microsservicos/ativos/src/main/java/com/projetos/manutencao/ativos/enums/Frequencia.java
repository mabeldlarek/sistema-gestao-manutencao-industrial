package com.projetos.manutencao.ativos.enums;

public enum Frequencia {
    IMPROVAVEL(1, "Improvável"),
    REMOTA(2, "Remota"),
    BAIXA(3, "Baixa"),
    MEDIA(4, "Média"),
    ALTA(5, "Alta");

    private final int valor;
    private final String descricao;

    Frequencia(int valor, String descricao) {
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
