package com.especialista.spring.jpa.controllers;

import com.especialista.spring.jpa.DTOs.ClienteDTO;
import com.especialista.spring.jpa.DTOs.ClienteProjecaoDTO;
import com.especialista.spring.jpa.DTOs.ClienteResumoDTO;
import com.especialista.spring.jpa.services.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> findAllClientes(){
        log.info("REQUEST - GET [findAllClientes]");

        List<ClienteDTO> cliente = clienteService.findAllClientes();

        log.info("RESPONSE - GET [findAllClientes]");
        return cliente;
    }

    @GetMapping(path = "/resumo")  //  busca lista de clientes sem os pedidos
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResumoDTO> findAllClientesResumo(){
        log.info("REQUEST - GET [findAllClientesResumo]");

        List<ClienteResumoDTO> cliente = clienteService.findAllClientesResumo();

        log.info("RESPONSE - GET [findAllClientesResumo]");
        return cliente;
    }

    @GetMapping(path = "/projecao-com-dto")  //  busca lista de clientes sem os pedidos
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteProjecaoDTO> findAllClienteProjecaoDTO(){
        log.info("REQUEST - GET [findAllClienteProjecaoDTO]");

        List<ClienteProjecaoDTO> cliente = clienteService.findAllClienteProjecaoDTO();

        log.info("RESPONSE - GET [findAllClienteProjecaoDTO]");
        return cliente;
    }

    @GetMapping(path = "/por-like-nome-spec")  //  busca lista de clientes sem os pedidos
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> findAllByLikeNomeSpec(String caractere){
        log.info("REQUEST - GET [findAllByLikeNomeSpec]");

        List<ClienteDTO> cliente = clienteService.findAllByLikeNomeSpec(caractere);

        log.info("RESPONSE - GET [findAllByLikeNomeSpec]");
        return cliente;
    }

}
