package com.especialista.spring.jpa.services;

import com.especialista.spring.jpa.DTOs.ProdutoDTO;
import com.especialista.spring.jpa.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;


    @Transactional(readOnly = true)  // busca lista de produtos pela categoriaId, usando consultas com a anotação @NamedQueries
    public List<ProdutoDTO> findAllProdutoslistarPorCategoria(String categoriaId) {

        return repository.findAllProdutoslistarPorCategoria( categoriaId)
            .stream()
            .map(ProdutoDTO::new)
            .toList();

    }


    @Transactional(readOnly = true) // busca lista de produtos usando consultas NamedNativeQuery com @SqlResultSetMapping para mapear retorno
    public List<ProdutoDTO> findAllProdutosUsandoSqlResultSetMappingComNamedNativeQuery() {
        return repository.findAllProdutosUsandoSqlResultSetMappingComNamedNativeQuery()
            .stream()
            .map(ProdutoDTO::new)
            .toList();
    }
}
