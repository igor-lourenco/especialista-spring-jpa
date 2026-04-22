package com.especialista.spring.jpa.DTOs;


import com.especialista.spring.jpa.entities.Cliente;

import java.time.LocalDate;
import java.util.List;

public record ClienteResumoDTO(

    Integer id,
    String nome,
    String cpf,
    String primeiroNome,
    String sexo,
    LocalDate dataNascimento

) {

    public ClienteResumoDTO(Cliente cliente) {
        this(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getPrimeiroNome(),
            cliente.getSexo() != null ? cliente.getSexo().name() : null,
            cliente.getDataNascimento()

        );
    }
}
