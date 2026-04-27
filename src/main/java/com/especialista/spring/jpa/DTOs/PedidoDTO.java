package com.especialista.spring.jpa.DTOs;

import com.especialista.spring.jpa.entities.Endereco;
import com.especialista.spring.jpa.entities.Pedido;
import com.especialista.spring.jpa.entities.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoDTO(

    Integer id,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") // controla apenas como o valor é serializado para JSON, Padrão ISO 8601 UTC
    LocalDateTime dataCriacao,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") // controla apenas como o valor é serializado para JSON, Padrão ISO 8601 UTC
    LocalDateTime dataUltimaAtualizacao,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") // controla apenas como o valor é serializado para JSON, Padrão ISO 8601 UTC
    LocalDateTime dataConclusao,

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
            pedido.getDataCriacao() != null ? pedido.getDataCriacao() : null,
            pedido.getDataUltimaAtualizacao() != null ? pedido.getDataUltimaAtualizacao() : null,
            pedido.getDataConclusao() != null ? pedido.getDataConclusao() : null,
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
