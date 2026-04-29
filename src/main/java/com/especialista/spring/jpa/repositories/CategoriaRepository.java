package com.especialista.spring.jpa.repositories;

import com.especialista.spring.jpa.DTOs.CategoriaDTO;
import com.especialista.spring.jpa.entities.Categoria;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends CustomJpaRepository<Categoria, Integer>, JpaSpecificationExecutor<Categoria> {


    @Query(name = "tb_categoria.buscarTodos", nativeQuery = true) // Externalizando consultas NamedNativeQuery com SqlResultSetMappings em um arquivo xml
    List<Categoria> findAllCategoriaArquivoXML();

    @Query(name = "tb_categoria.buscarTodos.CategoriaDTO", nativeQuery = true) // Externalizando consultas NamedNativeQuery com SqlResultSetMappings em um arquivo xml
    List<CategoriaDTO> findAllCategoriaDTOArquivoXML();

}
