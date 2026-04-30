package com.especialista.spring.jpa.repositories;

import com.especialista.spring.jpa.entities.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Integer>, JpaSpecificationExecutor<Pedido> {


    @Query(name = "Pedido.findAllPedidosArquivoXML") //  Externalizando consultas NamedQuery com JPQL em um arquivo xml
    List<Pedido> findAllPedidosArquivoXML();


    @Query(value = "FROM Pedido p")
    @EntityGraph(attributePaths = {"notaFiscal", "pagamento", "itensPedido"},
        type = EntityGraph.EntityGraphType.FETCH // por padrão é FETCH(jakarta.persistence.fetchgraph)
    )
    List<Pedido> findTodos();
}
