package com.projetos.manutencao.ativos.enums;

public enum UnidadeMedida {
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

    RPM("rpm");

    private final String simbolo;

    UnidadeMedida(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}
