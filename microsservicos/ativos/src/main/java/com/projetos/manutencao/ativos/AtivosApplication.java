package com.projetos.manutencao.ativos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.projetos.manutencao.ativos")
public class AtivosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtivosApplication.class, args);
	}

}
