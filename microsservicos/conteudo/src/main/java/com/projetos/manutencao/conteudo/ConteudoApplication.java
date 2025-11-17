package com.projetos.manutencao.conteudo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ConteudoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConteudoApplication.class, args);
	}

}
