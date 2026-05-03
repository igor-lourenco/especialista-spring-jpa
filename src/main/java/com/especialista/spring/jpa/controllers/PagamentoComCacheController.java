package com.especialista.spring.jpa.controllers;

import com.especialista.spring.jpa.DTOs.PagamentoDTO;
import com.especialista.spring.jpa.DTOs.PagamentoResumoDTO;
import com.especialista.spring.jpa.services.PagamentoComCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/pagamentos/cache")
public class PagamentoComCacheController {

    private final PagamentoComCacheService service;


    @GetMapping(path = "/usando-anotacao-cacheable1")   //  busca pagamento usando anotação Cacheable
    @ResponseStatus(HttpStatus.OK)
    public PagamentoDTO findPagamentoDTOByIdWithEhCache(Integer id){
        log.info("REQUEST - GET [findPagamentoDTOByIdWithEhCache]");

        PagamentoDTO obj = service.findPagamentoDTOByIdWithCache(id);

        log.info("RESPONSE - GET [findPagamentoDTOByIdWithEhCache]");
        return obj;
    }


    @GetMapping(path = "/usando-anotacao-cacheable2")   //  busca pagamento usando anotação Cacheable
    @ResponseStatus(HttpStatus.OK)
    public PagamentoResumoDTO findPagamentoResumoDTOByIdWithEhCache(Integer id){
        log.info("REQUEST - GET [findPagamentoResumoDTOByIdWithEhCache]");

        PagamentoResumoDTO obj = service.findPagamentoResumoDTOByIdWithCache(id);

        log.info("RESPONSE - GET [findPagamentoResumoDTOByIdWithEhCache]");
        return obj;
    }


    @GetMapping(path = "/usando-api-cachemanager")  //  busca pagamento usando API CacheManager
    @ResponseStatus(HttpStatus.OK)
    public PagamentoDTO findPagamentoDTOByIdWithAPICacheManager(Integer id){
        log.info("REQUEST - GET [findPagamentoDTOByIdWithAPICacheManager]");

        PagamentoDTO obj = service.findPagamentoDTOByIdWithAPICacheManager(id);

        log.info("RESPONSE - GET [findPagamentoDTOByIdWithAPICacheManager]");
        return obj;
    }
}
