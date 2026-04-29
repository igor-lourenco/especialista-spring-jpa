package com.especialista.spring.jpa.services;

import com.especialista.spring.jpa.DTOs.CategoriaDTO;
import com.especialista.spring.jpa.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    @Transactional(readOnly = true) // Externalizando consultas NamedNativeQuery com SqlResultSetMappings em um arquivo xml
    public List<CategoriaDTO> findAllCategoriaArquivoXML() {

        return repository.findAllCategoriaArquivoXML()
            .stream()
            .map(CategoriaDTO::new)
            .toList();
    }

    @Transactional(readOnly = true) // Externalizando consultas NamedNativeQuery com SqlResultSetMappings com retorno DTO em um arquivo xml
    public List<CategoriaDTO> findAllCategoriaDTOArquivoXML() {

        return repository.findAllCategoriaDTOArquivoXML();
    }
}
