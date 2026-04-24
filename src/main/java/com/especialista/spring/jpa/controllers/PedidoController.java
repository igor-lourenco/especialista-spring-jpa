package com.especialista.spring.jpa.controllers;

import com.especialista.spring.jpa.DTOs.PedidoDTO;
import com.especialista.spring.jpa.services.PedidoService;
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
@RequestMapping(path = "/v1/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;


    @GetMapping(path = "/join-fetch-spec")  //  busca lista de clientes sem os pedidos
    @ResponseStatus(HttpStatus.OK)
    public List<PedidoDTO> findAllUsandoJoinFetchESpec(){
        log.info("REQUEST - GET [findAllUsandoJoinFetchESpec]");

        List<PedidoDTO> pedido = pedidoService.findAllUsandoJoinFetchESpec();

        log.info("RESPONSE - GET [findAllUsandoJoinFetchESpec]");
        return pedido;
    }

}
