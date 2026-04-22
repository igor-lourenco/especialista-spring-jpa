package com.especialista.spring.jpa.listeners;

import com.especialista.spring.jpa.entities.Pedido;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class GerarNotaFiscalListener {

    private NotaFiscalServiceListener notaFiscalService = new NotaFiscalServiceListener();


    @PrePersist // Executa ANTES de persistir no banco de dados
    @PreUpdate // Executa ANTES de atualizar no banco de dados
    public void gerar(Pedido pedido) {

        if (pedido.isPago() && pedido.getNotaFiscal() == null) {
            notaFiscalService.gerar(pedido);
        }
    }
}
