package com.compras.spring.boot.services.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException() {
        super("O Produto informado não existe ou o endereço de email não pertence a nenhum cliente, por favor verifique os dados informados.");
    }
}
