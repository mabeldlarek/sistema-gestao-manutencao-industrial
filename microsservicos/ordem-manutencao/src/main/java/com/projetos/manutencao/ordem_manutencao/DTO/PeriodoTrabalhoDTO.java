package com.projetos.manutencao.ordem_manutencao.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PeriodoTrabalhoDTO {
    @NotNull(message = "A data/hora de início é obrigatória.")
    private LocalDateTime inicio;

    private LocalDateTime fim;
}
