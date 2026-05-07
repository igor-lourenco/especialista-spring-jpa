package com.especialista.spring.jpa.controllers;

import com.especialista.spring.jpa.DTOs.CategoriaDTO;
import com.especialista.spring.jpa.services.CategoriaComLockOtimistaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/categorias/lock-otimista")
public class CategoriaComLockOtimistaController {

    private final CategoriaComLockOtimistaService service;


    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK) //
    public CategoriaDTO updateComLockOtimista(@PathVariable Integer id){
        log.info("REQUEST - GET [updateComLockOtimista]");

        CategoriaDTO cliente = service.updateComLockOtimista(id);

        log.info("RESPONSE - GET [updateComLockOtimista]");
        return cliente;
    }

}
