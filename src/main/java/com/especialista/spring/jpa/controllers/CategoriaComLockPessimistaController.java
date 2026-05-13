package com.especialista.spring.jpa.controllers;

import com.especialista.spring.jpa.DTOs.CategoriaDTO;
import com.especialista.spring.jpa.services.CategoriaComLockPessimista_READ_Service;
import com.especialista.spring.jpa.services.CategoriaComLockPessimista_WRITE_Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/categorias/lock-pessimista")
public class CategoriaComLockPessimistaController {

    private final CategoriaComLockPessimista_READ_Service readService;
    private final CategoriaComLockPessimista_WRITE_Service writeService;


    @PutMapping(path = "/read/{id}")
    @ResponseStatus(HttpStatus.OK) //
    public CategoriaDTO updateComLockPessimistaUsando_READ(@PathVariable Integer id, Integer contador){
        log.info("REQUEST - PUT [updateComLockPessimistaUsando_READ]");

        log.info("Contador: {}", contador);

        CategoriaDTO cliente = readService.updateComLockPessimistaUsando_READ(id, contador);

        log.info("RESPONSE - PUT [updateComLockPessimistaUsando_READ] :: " + cliente.nome());
        return cliente;
    }


    @PutMapping(path = "/write/{id}")
    @ResponseStatus(HttpStatus.OK) //
    public CategoriaDTO updateComLockPessimistaUsando_WRITE(@PathVariable Integer id, Integer contador){
        log.info("REQUEST - PUT [updateComLockPessimistaUsando_WRITE]");

        log.info("Contador: {}", contador);

        CategoriaDTO cliente = writeService.updateComLockPessimistaUsando_WRITE(id, contador);

        log.info("RESPONSE - PUT [updateComLockPessimistaUsando_WRITE] :: " + cliente.nome());
        return cliente;
    }

}
