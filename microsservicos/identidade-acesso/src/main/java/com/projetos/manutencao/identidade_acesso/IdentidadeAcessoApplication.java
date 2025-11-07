package com.projetos.manutencao.identidade_acesso;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EntityScan("com.projetos.manutencao.identidade_acesso.model")
@EnableMongoRepositories(basePackages = "com.projetos.manutencao.identidade_acesso.repository.mongo")
@EnableJpaRepositories("com.projetos.manutencao.identidade_acesso.repository")
public class IdentidadeAcessoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentidadeAcessoApplication.class, args);
	}

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
