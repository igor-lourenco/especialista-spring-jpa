package com.especialista.spring.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

// Especifica ao Spring Data JPA para NÃO criar um bean Spring a partir dessa interface, ou seja, essa interface pode
// ser herdada, mas NÃO pode gerar um bean Spring por si só. Assim durante o startup da aplicação o
// Spring Data vai varrer o classpath e vai excluir a criação do bean para esse repositório, evitando tentativa de instanciar um repositório genérico
@NoRepositoryBean
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {

    /** Busca o último registro criado na tabela pelo ID (Obs: ID tem que ser sequencial)*/
    Optional<T> findTheLastCreated(String... atributePaths);

}
