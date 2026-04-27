package com.especialista.spring.jpa.repositories;

import com.especialista.spring.jpa.entities.Pagamento;
import com.especialista.spring.jpa.entities.StatusPagamento;

import java.util.List;

public interface CustomPagamentoRepository {

    List<Pagamento> findAllByStatus(StatusPagamento status);
}
