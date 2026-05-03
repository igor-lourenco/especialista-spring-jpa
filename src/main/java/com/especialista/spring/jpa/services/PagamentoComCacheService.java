package com.especialista.spring.jpa.services;

import com.especialista.spring.jpa.DTOs.PagamentoDTO;
import com.especialista.spring.jpa.DTOs.PagamentoResumoDTO;
import com.especialista.spring.jpa.repositories.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class PagamentoComCacheService {

    private final PagamentoRepository repository;
    private final CacheManager cacheManager;


//  sempre armazena o valor retornado pelo método anotado com @Cacheable, nesse caso o PagamentoDTO
//  obs: se usar o mesmo 'value' para o retorno de classes diferentes gera exception e bugs na aplicação
    @Cacheable(value = "pagamentoDTO", key = "#id")
    @Transactional(readOnly = true)
    public PagamentoDTO findPagamentoDTOByIdWithCache(Integer id) {

        return repository.findById(id)
            .map(PagamentoDTO::new)
            .orElseThrow(() ->
                new RuntimeException("Pagamento não encontrado"));
    }

//  sempre armazena o valor retornado pelo método anotado com @Cacheable, nesse caso o PagamentoResumoDTO
//  obs: se usar o mesmo 'value' para o retorno de classes diferentes gera exception e bugs na aplicação
    @Cacheable(value = "pagamentoResumoDTO", key = "#id")
    @Transactional(readOnly = true)
    public PagamentoResumoDTO findPagamentoResumoDTOByIdWithCache(Integer id) {

        return repository.findById(id)
            .map(PagamentoResumoDTO::new)
            .orElseThrow(() ->
                new RuntimeException("Pagamento não encontrado"));
    }


//  Usando a API CacheManager para buscar e manipular o cache programaticamente
//  esse método faz a mesma coisa que o @Cacheable(value = "pagamentoDTO", key = "#id")
    @Transactional(readOnly = true)
    public PagamentoDTO findPagamentoDTOByIdWithAPICacheManager(Integer id) {

        Cache cache = cacheManager.getCache("pagamentoDTO"); // para garantir que esse cache existe no ehcache.xml
        if (cache == null)  throw new IllegalStateException("Cache 'pagamentoDTO' não configurado");

        PagamentoDTO cached = cache.get(id, PagamentoDTO.class); // busca no cache o dto usando o id e tipo esperado
        if (cached != null) return cached; // retorna o dto que está no cache

        PagamentoDTO dto = repository.findById(id)
            .map(PagamentoDTO::new)
            .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        cache.put(id, dto); // Armazena o DTO no cache
        return dto;
    }

}
