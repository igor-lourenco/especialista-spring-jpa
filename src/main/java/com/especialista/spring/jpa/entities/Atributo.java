package com.especialista.spring.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

// Especifica que essa classe é embutível como parte intrínseca de uma entidade
// Cada uma das propriedades ou campos persistentes desse objeto é mapeada para a tabela do banco
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Atributo {

    @Column(length = 100, nullable = false)
    private String nome;

    private String valor;
}
