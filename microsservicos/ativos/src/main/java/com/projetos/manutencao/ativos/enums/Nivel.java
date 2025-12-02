package com.projetos.manutencao.ativos.enums;

public enum Nivel {
    A(4, "A", 3.5),
    B(3, "B", 3.49),
    C(2, "C", 2.49),
    D(1, "D", 1.49),
    INDEFINIDO(0, "INDEFINIDO", 0)
    ;
    private final int valor;
    private final String descricao;
    private final double faixaDeAceite;

    Nivel(int valor, String descricao, double faixaDeAceite) {
        this.valor = valor;
        this.descricao = descricao;
        this.faixaDeAceite = faixaDeAceite;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getFaixaDeAceite() {
        return faixaDeAceite;
    }
}
