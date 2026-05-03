package com.especialista.spring.jpa.DTOs;

import com.especialista.spring.jpa.entities.Pagamento;
import com.especialista.spring.jpa.entities.StatusPagamento;

public record PagamentoResumoDTO(
    Integer id,
    StatusPagamento statusPedido
) {

    public PagamentoResumoDTO(Pagamento pagamento){
        this(
            pagamento.getId(),
            pagamento.getStatus()
        );
    }
}
