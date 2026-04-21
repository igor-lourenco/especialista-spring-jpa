package com.especialista.spring.jpa.listeners;

import jakarta.persistence.PostLoad;

public class GenericoListener {

    @PostLoad // Executa APÓS carregar pedido no banco de dados
    public void logCarregamento(Object obj){
        System.out.println("Entidade: " + obj.getClass().getSimpleName() + " foi carregada.");
    }
}
