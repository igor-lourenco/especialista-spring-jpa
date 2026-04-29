package com.especialista.spring.jpa.controllers;

import com.especialista.spring.jpa.DTOs.CategoriaDTO;
import com.especialista.spring.jpa.services.CategoriaService;
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
@RequestMapping(path = "/v1/categorias")
public class CategoriaController {

    private final CategoriaService service;


    @GetMapping(path = "/externalizando-consultas-named-native-query-no-arquivo-xml")
    @ResponseStatus(HttpStatus.OK) // Externalizando consultas NamedNativeQuery com SqlResultSetMappings em um arquivo xml
    public List<CategoriaDTO> findAllCategoriaArquivoXML(){
        log.info("REQUEST - GET [findAllCategoriaArquivoXML]");

        List<CategoriaDTO> cliente = service.findAllCategoriaArquivoXML();

        log.info("RESPONSE - GET [findAllCategoriaArquivoXML]");
        return cliente;
    }


    @GetMapping(path = "/externalizando-consultas-named-native-query-com-DTO-no-arquivo-xml")
    @ResponseStatus(HttpStatus.OK)// Externalizando consultas NamedNativeQuery com SqlResultSetMappings com retorno DTO em um arquivo xml
    public List<CategoriaDTO> findAllCategoriaDTOArquivoXML(){
        log.info("REQUEST - GET [findAllCategoriaDTOArquivoXML]");

        List<CategoriaDTO> cliente = service.findAllCategoriaDTOArquivoXML();

        log.info("RESPONSE - GET [findAllCategoriaDTOArquivoXML]");
        return cliente;
    }

}
