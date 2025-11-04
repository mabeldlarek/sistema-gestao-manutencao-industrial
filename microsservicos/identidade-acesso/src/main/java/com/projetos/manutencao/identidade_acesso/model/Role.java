package com.projetos.manutencao.identidade_acesso.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    private String name;

    public enum Values{

        ADMIN (1L),
        BASIC (2L);

        long roleId;

        Values(long roleId){
            this.roleId = roleId;
        }
    }


}
