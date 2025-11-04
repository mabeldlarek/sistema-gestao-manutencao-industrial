package com.projetos.manutencao.identidade_acesso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.projetos.manutencao.identidade_acesso.model")
@EnableJpaRepositories("com.projetos.manutencao.identidade_acesso.repository")
public class IdentidadeAcessoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentidadeAcessoApplication.class, args);
	}

}
