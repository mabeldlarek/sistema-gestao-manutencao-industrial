package com.projetos.manutencao.ordem_manutencao.enums;

public enum UnidadeFrequencia {
    CELSIUS("°C"),
    FAHRENHEIT("°F"),
    KELVIN("K"),

    BAR("bar"),
    PSI("psi"),
    PASCAL("Pa"),

    MMS("mm/s"),
    G("g"),

    PORCENTAGEM("%"),

    AMPERE("A"),

    VOLT("V"),

    WATT("W"),

    LITROS_POR_MINUTO("L/min"),

    METROS("m"),

    METROS_POR_SEGUNDO("m/s"),

    RPM("rpm"),
    DIA("DIA"),
    SEMANA("SEM"),
    MES("MES"),
    ANO("ANO");

    private final String simbolo;

    UnidadeFrequencia(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}