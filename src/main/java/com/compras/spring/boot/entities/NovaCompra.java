package com.compras.spring.boot.entities;

import java.io.Serializable;

public class NovaCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String produto;
    private Integer quantidade;

    public NovaCompra() {
    }

    public NovaCompra(String email, String produto, Integer quantidade) {
        this.email = email;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
