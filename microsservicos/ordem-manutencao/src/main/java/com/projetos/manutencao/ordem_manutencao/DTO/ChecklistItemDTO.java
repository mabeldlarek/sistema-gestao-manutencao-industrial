package com.projetos.manutencao.ordem_manutencao.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistItemDTO {
    @NotBlank
    private String descricao;
    @NotNull
    private Boolean concluido;
}