package com.especialista.spring.jpa.DTOs;


import com.especialista.spring.jpa.entities.Cliente;

import java.time.LocalDate;
import java.util.List;

public record ClienteDTO(

    Integer id,
    String nome,
    String cpf,
    String primeiroNome,
    String sexo,
    LocalDate dataNascimento,
    List<Integer> pedidosIds

) {

    public ClienteDTO(Cliente cliente) {
        this(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getPrimeiroNome(),
            cliente.getSexo() != null ? cliente.getSexo().name() : null,
            cliente.getDataNascimento(),
            cliente.getPedidos() == null
                ? List.of()
                : cliente.getPedidos()
                .stream()
                .map(pedido -> pedido.getId())
                .toList()
        );
    }
}
