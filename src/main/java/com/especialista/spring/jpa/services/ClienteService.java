package com.especialista.spring.jpa.services;

import com.especialista.spring.jpa.DTOs.ClienteDTO;
import com.especialista.spring.jpa.DTOs.ClienteProjecaoDTO;
import com.especialista.spring.jpa.DTOs.ClienteResumoDTO;
import com.especialista.spring.jpa.repositories.ClienteRepository;
import com.especialista.spring.jpa.specifications.ClienteSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAllClientes() {


        return clienteRepository.findAllClientes()
            .stream()
            .map(ClienteDTO::new)
            .toList();

    }

    @Transactional(readOnly = true)  //  busca lista de clientes sem os pedidos
    public List<ClienteResumoDTO> findAllClientesResumo() {

        return clienteRepository.findAllClientesResumo()
            .stream()
            .map(ClienteResumoDTO::new)
            .toList();

    }

    @Transactional(readOnly = true)  //  busca lista de clientes sem os pedidos
    public List<ClienteProjecaoDTO> findAllClienteProjecaoDTO() {

        return clienteRepository.findAllClienteProjecaoDTO();

    }


    @Transactional(readOnly = true)  //  busca lista de clientes que contem o caractere
    public List<ClienteDTO> findAllByLikeNomeSpec(String caractere) {


        return clienteRepository.findAll(ClienteSpec.findAllByLikeCaractere(caractere))
            .stream()
            .map(ClienteDTO::new)
            .toList();

    }
}
