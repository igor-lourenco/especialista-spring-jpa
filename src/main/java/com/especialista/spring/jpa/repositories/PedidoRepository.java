package com.especialista.spring.jpa.repositories;

import com.especialista.spring.jpa.entities.Pedido;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Integer>, JpaSpecificationExecutor<Pedido> {


    @Query(name = "Pedido.findAllPedidosArquivoXML")
    List<Pedido> findAllPedidosArquivoXML();
}
