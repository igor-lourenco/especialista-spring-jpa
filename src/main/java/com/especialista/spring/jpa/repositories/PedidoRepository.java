package com.especialista.spring.jpa.repositories;

import com.especialista.spring.jpa.entities.Pedido;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Integer>, JpaSpecificationExecutor<Pedido> {

}
