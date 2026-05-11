package com.especialista.spring.jpa.repositories;

import com.especialista.spring.jpa.DTOs.CategoriaDTO;
import com.especialista.spring.jpa.entities.Categoria;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends CustomJpaRepository<Categoria, Integer>, JpaSpecificationExecutor<Categoria> {


    @Query(name = "tb_categoria.buscarTodos", nativeQuery = true) // Externalizando consultas NamedNativeQuery com SqlResultSetMappings em um arquivo xml
    List<Categoria> findAllCategoriaArquivoXML();

    @Query(name = "tb_categoria.buscarTodos.CategoriaDTO", nativeQuery = true) // Externalizando consultas NamedNativeQuery com SqlResultSetMappings em um arquivo xml
    List<CategoriaDTO> findAllCategoriaDTOArquivoXML();



//  Usar quando:
//  - Quer forçar falha antecipada
//  - Quer reservar o registro
//  - Quer impedir atualizações silenciosas
//  - Precisa garantir ordenação lógica
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT) // Incrementa a versão no flush mesmo sem alteração, isso garante que qualquer outro update concorrente falhe
    @Query("""
        SELECT c
        FROM Categoria c
        LEFT JOIN FETCH c.categoriaPai
        WHERE c.id = :id
    """)
    Optional<Categoria> findByIdComLockOtimistaUsandoAnotacao(@Param("id") Integer id);



    @Lock(LockModeType.PESSIMISTIC_READ)

    @QueryHints({
        @QueryHint(
            name = "jakarta.persistence.lock.timeout",
            value = "3000" // em milissegundos
        )
    })
    @Query("""
        SELECT c
        FROM Categoria c
        LEFT JOIN FETCH c.categoriaPai
        WHERE c.id = :id
    """)
    Optional<Categoria> updateComLockPessimistaUsando_READ(@Param("id") Integer id);

}
