package com.compras.spring.boot.services.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException{

    public ProdutoNaoEncontradoException(Object id) {
        super("Produto não encontrado. ID: "+ id);
    }
}
