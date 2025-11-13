package com.projetos.manutencao.ativos.enums;

public enum Peso {
    PRODUCAO(0.30, "Produção"),
    SEGURANCA(0.30, "Segurança"),
    AMBIENTAL(0.10, "Ambiental"),
    CUSTO_REPARO(0.15, "Custo de Reparo"),
    FALHA(0.15, "Falha");

    private final double valor;
    private final String categoria;

    Peso(double valor, String categoria) {
        this.valor = valor;
        this.categoria = categoria;
    }

    public double getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }
}
