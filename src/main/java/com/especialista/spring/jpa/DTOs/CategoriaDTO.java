package com.especialista.spring.jpa.DTOs;


import com.especialista.spring.jpa.entities.Categoria;

public record CategoriaDTO(
    Integer id,
    String nome

) {

    public CategoriaDTO(Categoria categoria) {
        this(
            categoria.getId(),
            categoria.getNome()
        );
    }
}
