package com.projetos.manutencao.ordem_manutencao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class OrdemManutencaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdemManutencaoApplication.class, args);
	}
    
}
