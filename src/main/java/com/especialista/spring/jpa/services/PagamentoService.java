package com.especialista.spring.jpa.services;

import com.especialista.spring.jpa.DTOs.PagamentoDTO;
import com.especialista.spring.jpa.entities.StatusPagamento;
import com.especialista.spring.jpa.repositories.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

}
