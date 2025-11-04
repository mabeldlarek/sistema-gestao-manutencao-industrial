package com.projetos.manutencao.identidade_acesso.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import com.projetos.manutencao.identidade_acesso.dto.auth.LoginRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(nullable = false)
    private String tipoUsuario;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable( name = "tb_users_roles", joinColumns = @JoinColumn(name = "user_id" ), inverseJoinColumns = @JoinColumn(name= "role_id"))
    private Set<Role> roles;


    @PrePersist
    protected void onCreate() {
        this.dataCriacao = new Date();
    }

    public boolean isLoginCorrect(LoginRequestDTO loginRequestDTO, PasswordEncoder passwordEncoder ) {
        return passwordEncoder.matches(loginRequestDTO.password(), this.senha);
    }
}
