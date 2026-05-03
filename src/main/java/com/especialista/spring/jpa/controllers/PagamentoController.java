package com.especialista.spring.jpa.controllers;

import com.especialista.spring.jpa.DTOs.PagamentoDTO;
import com.especialista.spring.jpa.DTOs.PagamentoResumoDTO;
import com.especialista.spring.jpa.entities.StatusPagamento;
import com.especialista.spring.jpa.services.PagamentoService;
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
@RequestMapping(path = "/v1/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    @GetMapping(path = "/usando-customizacao-repositorio-especifica")   //  busca lista de pagamentos a partir do status especificado
    @ResponseStatus(HttpStatus.OK)
    public List<PagamentoDTO> findAllByStatus(StatusPagamento status){
        log.info("REQUEST - GET [findAllByStatus]");

        List<PagamentoDTO> obj = service.findAllByStatus(status);

        log.info("RESPONSE - GET [findAllByStatus]");
        return obj;
    }


    @GetMapping(path = "/usando-anotacao-ehcache1")   //  busca pagamento usando anotação do EhCache
    @ResponseStatus(HttpStatus.OK)
    public PagamentoDTO findPagamentoDTOByIdWithEhCache(Integer id){
        log.info("REQUEST - GET [findAllByStatus]");

        PagamentoDTO obj = service.findPagamentoDTOByIdWithEhCache(id);

        log.info("RESPONSE - GET [findAllByStatus]");
        return obj;
    }

    @GetMapping(path = "/usando-anotacao-ehcache2")   //  busca pagamento usando anotação do EhCache
    @ResponseStatus(HttpStatus.OK)
    public PagamentoResumoDTO findPagamentoResumoDTOByIdWithEhCache(Integer id){
        log.info("REQUEST - GET [findAllByStatus]");

        PagamentoResumoDTO obj = service.findPagamentoResumoDTOByIdWithEhCache(id);

        log.info("RESPONSE - GET [findAllByStatus]");
        return obj;
    }


}
