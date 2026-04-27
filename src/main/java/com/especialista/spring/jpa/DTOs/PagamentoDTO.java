package com.especialista.spring.jpa.DTOs;

import com.especialista.spring.jpa.entities.Pagamento;
import com.especialista.spring.jpa.entities.StatusPagamento;
import com.especialista.spring.jpa.enums.TipoPagamento;
import jakarta.persistence.DiscriminatorValue;

public record PagamentoDTO(
    Integer id,
    String tipoPagamento,
    StatusPagamento statusPedido
) {

    public PagamentoDTO(Pagamento pagamento){
        this(
            pagamento.getId(),
            TipoPagamento.valueOfDescricao(
                pagamento.getClass().getAnnotation(DiscriminatorValue.class).value()),
            pagamento.getStatus()
        );
    }
}
