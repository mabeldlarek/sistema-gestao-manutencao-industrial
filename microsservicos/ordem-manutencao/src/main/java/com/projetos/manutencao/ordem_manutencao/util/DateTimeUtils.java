package com.projetos.manutencao.ordem_manutencao.util;

import com.projetos.manutencao.ordem_manutencao.enums.UnidadeFrequencia;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public final class DateTimeUtils {

    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    private DateTimeUtils() {
    }

    public static ZonedDateTime toZonedDateTime(Date date) {
        return date.toInstant().atZone(DEFAULT_ZONE);
    }

    public static Date toDate(ZonedDateTime zdt) {
        return Date.from(zdt.toInstant());
    }

    public static ZonedDateTime addFrequency(ZonedDateTime base, UnidadeFrequencia uf, int value) {

        switch (uf) {
            case DIA:
                return base.plusDays(value);

            case SEMANA:
                return base.plusWeeks(value);

            case MES:
                return base.plusMonths(value);

            case ANO:
                return base.plusYears(value);

            case HORAS:
                return base.plusHours(value);

            default:
                throw new IllegalArgumentException("Unidade de frequência não suportada: " + uf);
        }
    }

    public static Date calcularProximaData(Date date, UnidadeFrequencia uf, int value) {
        ZonedDateTime zdt = toZonedDateTime(date);
        ZonedDateTime newZdt = addFrequency(zdt, uf, value);
        return toDate(newZdt);
    }
}