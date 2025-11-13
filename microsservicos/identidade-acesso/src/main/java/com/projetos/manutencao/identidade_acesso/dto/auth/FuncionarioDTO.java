package com.projetos.manutencao.identidade_acesso.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class FuncionarioDTO {

    @NotNull(message = "O número de matricula é obrigatório")
    private String matricula;

    @NotBlank(message = "O nome é obrigatório")
    @Pattern( regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]{3,50}$", message = "O nome deve conter apenas letras e espaços, com no máximo 50 caracteres" )
    @NotBlank(message = "O nome do funcionário é obrigatório")
    private String nome;

    @NotBlank(message = "O cargo é obrigatório")
    @Pattern(
            regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]{1,50}$",
            message = "O cargo deve conter apenas letras e espaços, com no máximo 50 caracteres"
    )

    @NotBlank(message = "Cargo é obrigatório")
    private String cargo;

    @NotNull(message = "Equipe é obrigatória")
    @Pattern(
            regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]{1,50}$",
            message = "O cargo deve conter apenas letras e espaços, com no máximo 50 caracteres"
    )
    @NotBlank(message = "Equipe é obrigatória")
    private String equipe;

    @NotNull(message = "A lista de especialidades é obrigatória")
    @NotEmpty(message = "É necessário informar ao menos uma especialidade")
    private List<@NotBlank(message = "Especialidade não pode ser vazia")
    @Pattern(
            regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]{1,50}$",
            message = "A especialidade deve conter apenas letras e espaços, com no máximo 50 caracteres"
    )String> especialidades;

    @NotNull
    @NotBlank(message = "Status é obrigatória")
    private String status;

    public FuncionarioDTO(String matricula, String nome, String cargo, String equipe, List<String> especialidades, String status) {
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
        this.equipe = equipe;
        this.especialidades = especialidades;
        this.status = status;
    }
}
