package com.especialista.spring.jpa.specifications;

import com.especialista.spring.jpa.entities.Cliente;
import com.especialista.spring.jpa.entities.Pedido;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

/** Classe que fabrica implementações do Specifications para o Cliente */
public class ClienteSpec {

    public static Specification<Cliente> findAllByLikeCaractere(String caractere){

        return (root, query, criteriaBuilder) -> {
//          SELECT c FROM Cliente c
//            LEFT JOIN FETCH c.contatos con
//            LEFT JOIN FETCH c.pedidos p
//            LEFT JOIN FETCH p.pagamento pag
//            LEFT JOIN FETCH p.notaFiscal nf

            root.fetch("contatos", JoinType.LEFT);                  // LEFT JOIN FETCH c.contatos con

            Join<Cliente, Pedido> joinPedidos = (Join<Cliente, Pedido>) root
                .<Cliente, Pedido>fetch("pedidos", JoinType.LEFT);  // LEFT JOIN FETCH c.pedidos p

            joinPedidos.fetch("pagamento", JoinType.LEFT);          // LEFT JOIN FETCH p.pagamento pag
            joinPedidos.fetch("notaFiscal", JoinType.LEFT);         // LEFT JOIN FETCH p.notaFiscal nf

            return criteriaBuilder.like(root.get("nome"), "%" + caractere + "%");

        };
    }
}
