package com.especialista.spring.jpa.DTOs;

import com.especialista.spring.jpa.entities.Produto;

public record ProdutoDTO(
    Integer id,
    String nome
) {
    public ProdutoDTO(Produto produto) {
        this(produto.getId(), produto.getNome());
    }
}
