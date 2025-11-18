package com.projetos.manutencao.ordem_manutencao.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistItem {

    private String descricao;
    private Boolean concluido;

}
