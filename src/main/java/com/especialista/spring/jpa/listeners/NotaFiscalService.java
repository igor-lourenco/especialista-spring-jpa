package com.especialista.spring.jpa.listeners;

import com.especialista.spring.jpa.entities.Pedido;

public class NotaFiscalService {

    private NotaFiscalService notaFiscalService;


    public void gerar(Pedido pedido){
        System.out.println(">>> Gerando nota fiscal para pedido: " + pedido.getId());
    }
}
