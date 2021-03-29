package com.compras.spring.boot.services.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException{

    public ClienteNaoEncontradoException(Object id) {
        super("Cliente não encontrado. Email: "+ id);
    }
}
