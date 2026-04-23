package com.especialista.spring.jpa.DTOs;


import com.especialista.spring.jpa.entities.Cliente;
import com.especialista.spring.jpa.entities.SexoCliente;

import java.time.LocalDate;

public record ClienteProjecaoDTO(

    Integer id,
    String nome,
    String cpf,
    String primeiroNome, // não é carregado pelo JPA
    SexoCliente sexo,
    LocalDate dataNascimento

) {
    public ClienteProjecaoDTO(
        Integer id,
        String nome,
        String cpf,
        SexoCliente sexo,
        LocalDate dataNascimento
    ) {
        this(
            id,
            nome,
            cpf,
            extrairPrimeiroNome(nome),
            sexo,
            dataNascimento
        );
    }

    private static String extrairPrimeiroNome(String nome) {
        if (nome == null || nome.isBlank()) return null;
        int idx = nome.indexOf(" ");
        return idx > 0 ? nome.substring(0, idx) : nome;
    }
}
