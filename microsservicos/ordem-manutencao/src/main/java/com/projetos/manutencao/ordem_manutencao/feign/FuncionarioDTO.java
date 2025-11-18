package com.projetos.manutencao.ordem_manutencao.feign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {
   private String nome;
   private String id;
}
