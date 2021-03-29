package com.compras.spring.boot.services.exceptions;

public class ProdutoProibidoException extends RuntimeException{

    public ProdutoProibidoException(Object id) {
        super("Produto proibido para menores de 18 anos. Idade do cliente: "+ id);
    }
}