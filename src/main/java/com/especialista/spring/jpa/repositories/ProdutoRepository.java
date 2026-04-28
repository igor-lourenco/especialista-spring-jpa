package com.especialista.spring.jpa.repositories;

import com.especialista.spring.jpa.entities.Produto;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Integer>, JpaSpecificationExecutor<Produto> {


    @Query(name = "Produto.listarPorCategoria") // usando a consulta que está na anotação @NamedQuerie da entidade
    List<Produto> findAllProdutoslistarPorCategoria(@Param(value = "categoriaId") String categoriaId);

    @Query(name = "tb_produto.listarTodos", nativeQuery = true) // usando a consulta que está na anotação @NamedNativeQuerie com @SqlResultSetMapping da entidade
    List<Produto> findAllProdutosUsandoSqlResultSetMappingComNamedNativeQuery();
}
