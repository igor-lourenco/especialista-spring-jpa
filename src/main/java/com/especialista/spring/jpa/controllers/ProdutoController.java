package com.especialista.spring.jpa.controllers;

import com.especialista.spring.jpa.DTOs.ProdutoDTO;
import com.especialista.spring.jpa.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/produtos")
public class ProdutoController {

    private final ProdutoService service;

    @GetMapping(path = "/usando-anotacao_NamedQueries-na-entidade")  // busca lista de produtos pela categoriaId, usando consultas com a anotação @NamedQueries
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoDTO> findAllProdutoslistarPorCategoria_usandoAnotacaoNamedQueriesNaEntidade(String categoriaId){
        log.info("REQUEST - GET [findAllProdutoslistarPorCategoria_usandoAnotacaoNamedQueriesNaEntidade]");


        List<ProdutoDTO> lista = service.findAllProdutoslistarPorCategoria(categoriaId);

        log.info("RESPONSE - GET [findAllProdutoslistarPorCategoria_usandoAnotacaoNamedQueriesNaEntidade]");
        return lista;
    }

    @GetMapping(path = "/usando-SqlResultSetMapping-com-NamedNativeQuery")  // busca lista de produtos usando consultas NamedNativeQuery com @SqlResultSetMapping para mapear retorno
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoDTO> findAllProdutos_usando_SqlResultSetMapping_com_NamedNativeQuery(){
        log.info("REQUEST - GET [findAllProdutos_usando_SqlResultSetMapping_com_NamedNativeQuery]");


        List<ProdutoDTO> lista = service.findAllProdutosUsandoSqlResultSetMappingComNamedNativeQuery();

        log.info("RESPONSE - GET [findAllProdutos_usando_SqlResultSetMapping_com_NamedNativeQuery]");
        return lista;
    }


}
