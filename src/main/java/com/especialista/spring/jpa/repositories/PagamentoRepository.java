package com.especialista.spring.jpa.repositories;

import com.especialista.spring.jpa.entities.Pagamento;
import com.especialista.spring.jpa.entities.StatusPagamento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends CustomJpaRepository<Pagamento, Integer>, CustomPagamentoRepository {

    List<Pagamento> findAllByStatus(StatusPagamento status);
}
