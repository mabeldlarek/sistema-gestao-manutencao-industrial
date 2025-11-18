package com.projetos.manutencao.ordem_manutencao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodoTrabalho {
    private LocalDateTime inicio;
    private LocalDateTime fim;
}