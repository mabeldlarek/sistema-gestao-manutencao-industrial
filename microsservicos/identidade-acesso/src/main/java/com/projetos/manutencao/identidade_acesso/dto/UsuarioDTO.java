package com.projetos.manutencao.identidade_acesso.dto;

import com.projetos.manutencao.identidade_acesso.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.Set;

public class UsuarioDTO {

    @NotBlank(message = "Nome de usuário é obrigatório.")
    private String nomeUsuario;

    @NotBlank(message = "E-mail é obrigatório.")
    @Email(message = "E-mail inválido.")
    private String email;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String senha;

    private Date dataCriacao;

    @NotNull(message = "Campo 'ativo' é obrigatório.")
    private Boolean ativo;

    @NotBlank(message = "Tipo de usuário é obrigatório.")
    private String tipoUsuario;

    @NotEmpty(message = "O usuário deve possuir ao menos 1 papel (role).")
    private Set<Role> roles;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nome) {
        this.nomeUsuario = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
