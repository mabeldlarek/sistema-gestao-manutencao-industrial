package com.projetos.manutencao.ordem_manutencao.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeriodoTrabalhoDTO {
    @NotNull(message = "A data/hora de início é obrigatória.")
    private LocalDateTime inicio;
    private LocalDateTime fim;
}
