package com.projetos.manutencao.identidade_acesso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD

@SpringBootApplication
=======
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.projetos.manutencao.identidade_acesso.repository")
>>>>>>> dev
public class IdentidadeAcessoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentidadeAcessoApplication.class, args);
	}

}
