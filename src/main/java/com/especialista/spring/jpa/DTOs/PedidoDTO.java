package com.especialista.spring.jpa.DTOs;

import com.especialista.spring.jpa.entities.Endereco;
import com.especialista.spring.jpa.entities.Pedido;
import com.especialista.spring.jpa.entities.StatusPedido;

import java.math.BigDecimal;

public record PedidoDTO(

    Integer id,
    String dataCriacao,
    String dataUltimaAtualizacao,
    String dataConclusao,
    BigDecimal total,
    StatusPedido status,
    Endereco enderecoEntrega,
    Integer clienteId,
    Integer quantidadeItens,
    Integer pagamentoId,
    Integer notaFiscalId
) {

    public PedidoDTO(Pedido pedido) {
        this(
            pedido.getId(),
            pedido.getDataCriacao() != null ? pedido.getDataCriacao().toString() : null,
            pedido.getDataUltimaAtualizacao() != null ? pedido.getDataUltimaAtualizacao().toString() : null,
            pedido.getDataConclusao() != null ? pedido.getDataConclusao().toString() : null,
            pedido.getTotal(),
            pedido.getStatus(),
            pedido.getEnderecoEntrega(),
            pedido.getCliente() != null ? pedido.getCliente().getId() : null,
            pedido.getItensPedido() != null ? pedido.getItensPedido().size() : null,
            pedido.getPagamento() != null ? pedido.getPagamento().getId() : null,
            pedido.getNotaFiscal() != null ? pedido.getNotaFiscal().getId() : null
        );
    }

}
