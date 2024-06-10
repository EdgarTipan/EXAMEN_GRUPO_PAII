package com.example.galaga.galaga;

import controller.GameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.galaga.galaga", "controller", "model", "repository"})
@EnableJpaRepositories(basePackages = "repository")
public class GalagaApplication implements CommandLineRunner {
	@Autowired
	GameController service;

	public static void main(String[] args) {
		SpringApplication.run(GalagaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Puedes inicializar datos aquí si es necesario
	}
}
