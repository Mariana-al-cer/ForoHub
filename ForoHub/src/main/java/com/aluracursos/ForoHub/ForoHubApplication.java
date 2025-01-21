package com.aluracursos.ForoHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.aluracursos.ForoHub.domain.usuario")
public class ForoHubApplication {

	public static void main(String[] args) {

		SpringApplication.run(ForoHubApplication.class, args);
	}
}