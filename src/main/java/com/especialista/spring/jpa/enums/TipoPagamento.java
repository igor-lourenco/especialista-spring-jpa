package com.especialista.spring.jpa.enums;

public enum TipoPagamento {

    PAGAMENTO_CARTAO("PagamentoCartao", "Pagamento com cartão"),
    PAGAMENTO_BOLETO("PagamentoBoleto", "Pagamento com boleto");

    private String nome;
    private String descricao;

    TipoPagamento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }


    public static String valueOfDescricao(String nome){

        for (TipoPagamento tipoPagamento : TipoPagamento.values()) {
            if(tipoPagamento.nome.equalsIgnoreCase(nome)){
                return tipoPagamento.descricao;
            }
        }

        return null;
    }
}
