package com.especialista.spring.jpa.services;

import com.especialista.spring.jpa.DTOs.PedidoComClienteDTO;
import com.especialista.spring.jpa.DTOs.PedidoDTO;
import com.especialista.spring.jpa.DTOs.PedidoResumoDTO;
import com.especialista.spring.jpa.repositories.PedidoRepository;
import com.especialista.spring.jpa.specifications.PedidoSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;


    @Transactional(readOnly = true)  //  busca lista de pedidos usando join fetch com entidades e specification
    public List<PedidoDTO> findAllUsandoJoinFetchESpec() {

        return pedidoRepository.findAll(PedidoSpec.findAllUsandoJoinFetchESpec())
            .stream()
            .map(PedidoDTO::new)
            .toList();

    }


    @Transactional(readOnly = true)
    public PedidoResumoDTO findTheLastCreated() { // Busca o último registro criado na tabela pelo ID (Obs: ID tem que ser sequencial)

        return pedidoRepository.findTheLastCreated("pagamento", "notaFiscal", "itensPedido")
            .map(PedidoResumoDTO::new)
            .orElseThrow(
            () ->  new IllegalArgumentException("Pedido não encontrado"));
    }


    @Transactional(readOnly = true)
    public List<PedidoDTO> findAllPedidosArquivoXML() {   //  Externalizando consultas NamedQuery com JPQL em um arquivo xml

        return pedidoRepository.findAllPedidosArquivoXML()
            .stream()
            .map(PedidoDTO::new)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> findTodos() { // usando @EntityGraph

        return pedidoRepository.findTodos()
            .stream()
            .map(PedidoDTO::new)
            .toList();
    }


    @Transactional(readOnly = true)
    public List<PedidoComClienteDTO> findPedidoComClienteUsandoNamedEntityGraph() { // usando @NamedEntityGraph na entidade + uso no repository

        return pedidoRepository.findPedidoComClienteUsandoNamedEntityGraph()
            .stream()
            .map(PedidoComClienteDTO::new)
            .toList();
    }
}
