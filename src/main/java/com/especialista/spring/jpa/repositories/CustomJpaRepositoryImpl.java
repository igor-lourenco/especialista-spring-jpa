package com.especialista.spring.jpa.repositories;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID>
    extends SimpleJpaRepository<T, ID>
    implements CustomJpaRepository<T, ID> {

    private EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityManager = entityManager;
    }

    @Override // busca o último registro criado na tabela pelo ID (Obs: ID tem que ser sequencial)
    public Optional<T> findTheLastCreated(String... attributePaths) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getDomainClass()); // Query vai retornar a ENTIDADE
        Root<T> root = criteriaQuery.from(getDomainClass());                            // FROM 'ENTIDADE' x

        EntityGraph<T> graph = entityManager.createEntityGraph(getDomainClass());
        for (String path : attributePaths) {
            addAttributePath(graph, path);
        }

        criteriaQuery.select(root)                                 // SELECT x
            .orderBy(criteriaBuilder.desc(root.get("id")));  // ORDER BY x.id DESC

        T entity = entityManager
            .createQuery(criteriaQuery)
            .setHint("jakarta.persistence.fetchgraph", graph) // só carrega o que está no graph, tudo fora do graph fica LAZY, ignora EAGER do mapeamento
            .setMaxResults(1)
            .getSingleResult();

        return Optional.ofNullable(entity);
    }

//  método utilitário se tiver paths aninhados por exemplo itensPedido.precoProduto no caso de Pedido
    private void addAttributePath(EntityGraph<?> graph, String path) {
        if (!path.contains(".")) {
            graph.addAttributeNodes(path);
            return;
        }

        String[] parts = path.split("\\.");
        Subgraph<?> subgraph = graph.addSubgraph(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            if (i == parts.length - 1) {
                subgraph.addAttributeNodes(parts[i]);
            } else {
                subgraph = subgraph.addSubgraph(parts[i]);
            }
        }
    }
}
