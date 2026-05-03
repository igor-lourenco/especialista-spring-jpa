package com.especialista.spring.jpa.services;

import com.especialista.spring.jpa.DTOs.PagamentoDTO;
import com.especialista.spring.jpa.DTOs.PagamentoResumoDTO;
import com.especialista.spring.jpa.entities.StatusPagamento;
import com.especialista.spring.jpa.repositories.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository repository;

    @Transactional(readOnly = true)  //  busca lista de pagamentos a partir do status especificado
    public List<PagamentoDTO> findAllByStatus(StatusPagamento status) {

        return repository.findAllByStatus(status)
            .stream()
            .map(PagamentoDTO::new)
            .toList();

    }

//  sempre armazena o valor retornado pelo método anotado com @Cacheable, nesse caso o PagamentoDTO
//  obs: se usar o mesmo 'value' para o retorno de classes diferentes gera exception e bugs na aplicação
    @Cacheable(value = "pagamentoDTO", key = "#id")
    @Transactional(readOnly = true)
    public PagamentoDTO findPagamentoDTOByIdWithEhCache(Integer id) {

        return repository.findById(id)
            .map(PagamentoDTO::new)
            .orElseThrow(() ->
                new RuntimeException("Pagamento não encontrado"));
    }

//  sempre armazena o valor retornado pelo método anotado com @Cacheable, nesse caso o PagamentoResumoDTO
//  obs: se usar o mesmo 'value' para o retorno de classes diferentes gera exception e bugs na aplicação
    @Cacheable(value = "pagamentoResumoDTO", key = "#id")
    @Transactional(readOnly = true)
    public PagamentoResumoDTO findPagamentoResumoDTOByIdWithEhCache(Integer id) {

        return repository.findById(id)
            .map(PagamentoResumoDTO::new)
            .orElseThrow(() ->
                new RuntimeException("Pagamento não encontrado"));
    }
}
