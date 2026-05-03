package com.especialista.spring.jpa;

import com.especialista.spring.jpa.repositories.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

// Altera para usar a nossa implementação como classe base do repositório, ou seja, define a classe base que o Spring Data usará
// para implementar todos os repositórios JPA do projeto.
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)

// Habilita o cache, isso ativa: @Cacheable, @CachePut, @CacheEvict para ser usados no service
@EnableCaching

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
