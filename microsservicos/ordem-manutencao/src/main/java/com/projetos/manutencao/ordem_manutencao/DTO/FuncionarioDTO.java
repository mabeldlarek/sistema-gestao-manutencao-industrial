package com.projetos.manutencao.ordem_manutencao.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {

   private String nome;
   private String matricula;
   private String cargo;
   private String id;

}
