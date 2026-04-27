package com.especialista.spring.jpa.repositories;

import com.especialista.spring.jpa.entities.Pagamento;
import com.especialista.spring.jpa.entities.StatusPagamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CustomPagamentoRepositoryImpl implements CustomPagamentoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Pagamento> findAllByStatus(StatusPagamento status) {

        String jpql = "SELECT p "
            + " FROM Pagamento p "
            + " LEFT JOIN FETCH p.pedido ped "
            + " LEFT JOIN FETCH ped.notaFiscal nf "
            + " WHERE p.status = :status";

        List<Pagamento> lista = entityManager.createQuery(jpql, Pagamento.class)
            .setParameter("status", status)
            .getResultList();

        return lista;
    }
}
