package com.compras.spring.boot.entities.dto;

import com.compras.spring.boot.entities.Compra;
import com.compras.spring.boot.services.CompraService;

import java.io.Serializable;

public class CompraDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nomeItem;
    private Double valor;
    private String cpf;
    private Double total;
    private Integer quantidade;

    public CompraDTO(String nomeItem, Double valor, String cpf, Double total, Integer quantidade) {
        this.nomeItem = nomeItem;
        this.valor = valor;
        this.cpf = cpf;
        this.total = total;
        this.quantidade = quantidade;
    }

    public CompraDTO() {
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}