package com.especialista.spring.jpa.controllers;

import com.especialista.spring.jpa.DTOs.ClienteDTO;
import com.especialista.spring.jpa.entities.Cliente;
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

}
