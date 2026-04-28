package com.especialista.spring.jpa.DTOs;

import com.especialista.spring.jpa.entities.Produto;

public record ProdutoDTO(
    Integer produtoId,
    String produtoNome
) {
    public ProdutoDTO(Produto produto) {
        this(produto.getId(), produto.getNome());
    }
}
