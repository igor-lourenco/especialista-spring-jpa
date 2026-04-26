package com.especialista.spring.jpa;

import com.especialista.spring.jpa.repositories.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

// Altera para usar a nossa implementação como classe base do repositório, ou seja, define a classe base que o Spring Data usará
// para implementar todos os repositórios JPA do projeto.
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
