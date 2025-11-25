package com.projetos.manutencao.ordem_manutencao.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcedimentoCheklistDTO {
    private List<String> passosChecklist;
}
