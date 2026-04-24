package com.especialista.spring.jpa.specifications;

import com.especialista.spring.jpa.entities.Cliente;
import com.especialista.spring.jpa.entities.ItemPedido;
import com.especialista.spring.jpa.entities.Pedido;
import com.especialista.spring.jpa.entities.Produto;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

/** Classe que fabrica implementações do Specifications para o Pedido */
public class PedidoSpec {

    public static Specification<Pedido> findAllUsandoJoinFetchESpec(){

        return (root, query, criteriaBuilder) -> {
//      String jpql1 = SELECT p FROM Pedido p
//          +  LEFT JOIN FETCH p.pagamento       // Pedido tem que ter Pagamento, não pode ser null (nesse caso tem uma exceção porque tem um pedido sem pagamento que foi criado diretamente no banco de dados)
//          +  JOIN FETCH p.cliente c           // Pedido tem que ter Cliente, não pode ser null
//          +  LEFT JOIN FETCH c.contatos con  // Usando LEFT JOIN FETCH para Cliente trazer contatos mesmo se for null
//          +  LEFT JOIN FETCH p.notaFiscal    // Usando LEFT JOIN FETCH para trazer a notaFiscal mesmo se for null
//          +  JOIN FETCH p.itensPedido itens // O pedido tem que ter ItemPedido, não pode ser null
//          +  JOIN FETCH itens.produto prod  // O ItemPedido tem que ter Produto, não pode ser null
//          +  LEFT JOIN FETCH prod.estoque   // Usando LEFT JOIN FETCH para trazer estoque mesmo se for null

            root.fetch("pagamento", JoinType.LEFT); // LEFT JOIN FETCH p.pagamento

            Join<Pedido, Cliente> joinCliente =
                (Join<Pedido, Cliente>) root.<Pedido, Cliente>fetch("cliente"); // JOIN FETCH p.cliente c

            joinCliente.fetch("contatos", JoinType.LEFT); // LEFT JOIN FETCH c.contatos con

            root.fetch("notaFiscal", JoinType.LEFT); // LEFT JOIN FETCH p.notaFiscal

            Join<Pedido, ItemPedido> joinItensPedido =
                (Join<Pedido, ItemPedido>) root.<Pedido, ItemPedido>fetch("itensPedido"); // JOIN FETCH p.itensPedido itens

            Join<ItemPedido, Produto> joinProduto =
                (Join<ItemPedido, Produto>) joinItensPedido.<ItemPedido, Produto>fetch("produto"); // JOIN FETCH itens.produto prod

            joinProduto.fetch("estoque", JoinType.LEFT); // LEFT JOIN FETCH prod.estoque

//           Specification SEMPRE precisa retornar um Predicate
//           e o conjunction() Retorna um Predicate que é sempre verdadeiro
            return criteriaBuilder.conjunction();
        };
    }
}
