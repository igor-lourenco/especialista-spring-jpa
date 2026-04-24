package com.especialista.spring.jpa.repositories;

import com.especialista.spring.jpa.DTOs.ClienteProjecaoDTO;
import com.especialista.spring.jpa.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {

    @Query("""
        SELECT c FROM Cliente c 
        LEFT JOIN FETCH c.contatos con 
        LEFT JOIN FETCH c.pedidos p 
        LEFT JOIN FETCH p.pagamento pag 
        LEFT JOIN FETCH p.notaFiscal nf
    """)
    List<Cliente> findAllClientes();


    @Query("""
        SELECT c FROM Cliente c 
        LEFT JOIN FETCH c.contatos con 
    """)
    List<Cliente> findAllClientesResumo();


    @Query("""
        SELECT new com.especialista.spring.jpa.DTOs.ClienteProjecaoDTO(
            c.id,
            c.nome,
            c.cpf,
            c.sexo,
            c.dataNascimento
        )
        FROM Cliente c
    """)
    List<ClienteProjecaoDTO> findAllClienteProjecaoDTO();


}
