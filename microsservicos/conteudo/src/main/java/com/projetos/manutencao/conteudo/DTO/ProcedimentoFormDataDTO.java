package com.projetos.manutencao.conteudo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedimentoFormDataDTO {
    private List<PecaDTO> pecasDisponiveis;

}
