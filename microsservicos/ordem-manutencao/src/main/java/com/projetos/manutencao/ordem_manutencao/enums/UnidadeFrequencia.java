package com.projetos.manutencao.ordem_manutencao.enums;

public enum UnidadeFrequencia {
    CELSIUS("°C", TipoFrequencia.TEMPERATURA),
    FAHRENHEIT("°F", TipoFrequencia.TEMPERATURA),
    KELVIN("K", TipoFrequencia.TEMPERATURA),

    BAR("bar", TipoFrequencia.PRESSAO),
    PSI("psi", TipoFrequencia.PRESSAO),
    PASCAL("Pa", TipoFrequencia.PRESSAO),

    MMS("mm/s", TipoFrequencia.VIBRACAO),
    G("g", TipoFrequencia.VIBRACAO),

    PORCENTAGEM("%", TipoFrequencia.UMIDADE),

    AMPERE("A", TipoFrequencia.CORRENTE),

    VOLT("V", TipoFrequencia.TENSAO),

    WATT("W", TipoFrequencia.POTENCIA),

    LITROS_POR_MINUTO("L/min", TipoFrequencia.FLUXO),

    METROS("m", TipoFrequencia.NIVEL),

    METROS_POR_SEGUNDO("m/s", TipoFrequencia.VELOCIDADE),

    RPM("rpm", TipoFrequencia.RPM),

    DIA("DIA", TipoFrequencia.TEMPO),
    SEMANA("SEM", TipoFrequencia.TEMPO),
    MES("MES", TipoFrequencia.TEMPO),
    ANO("ANO", TipoFrequencia.TEMPO),
    HORAS("HORAS", TipoFrequencia.TEMPO);

    private final String simbolo;

    private final TipoFrequencia grandeza;

    UnidadeFrequencia(String simbolo, TipoFrequencia grandeza) {
        this.simbolo = simbolo;
        this.grandeza = grandeza;
    }

    public String getSimbolo() {
        return simbolo;
    }
}