package com.especialista.spring.jpa.DTOs;


import com.especialista.spring.jpa.entities.Categoria;

import java.util.List;

public record CategoriaComProdutoDTO(
    Integer id,
    String nome,
    List<ProdutoDTO> produtos

) {

    public CategoriaComProdutoDTO(Categoria categoria) {
        this(
            categoria.getId(),
            categoria.getNome(),
            categoria.getProdutos() == null ? List.of()
                : categoria.getProdutos()
                .stream()
                .map(p -> new ProdutoDTO(p.getId(), p.getNome()))
                .toList()
        );
    }
}
