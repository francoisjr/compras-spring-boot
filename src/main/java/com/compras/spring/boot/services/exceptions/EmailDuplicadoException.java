package com.compras.spring.boot.services.exceptions;

public class EmailDuplicadoException  extends RuntimeException{

    public EmailDuplicadoException(Object id) {
        super("Existe um cliente cadastrado com o email informado. Email: "+ id);
    }
}
