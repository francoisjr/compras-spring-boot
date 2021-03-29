package com.compras.spring.boot.entities;

import com.compras.spring.boot.entities.pk.ItemCompraPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_item_compra")
public class ItemCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ItemCompraPK id = new ItemCompraPK();

    private Integer quantidade;
    private Double preco;

    public ItemCompra() {
    }

    public ItemCompra(Compra compra, Produto produto, Integer quantidade) {
        id.setCompra(compra);
        id.setProduto(produto);
        this.quantidade = quantidade;
        this.preco = produto.getValor();
    }


    @JsonIgnore
    public Compra getCompra(){
        return id.getCompra();
    }

    public void setCompra(Compra compra){
        id.setCompra(compra);
    }


    public Produto getProduto(){
        return id.getProduto();
    }

    public void setProduto(Produto produto){
        id.setProduto(produto);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }



   @JsonIgnore
    public Double getSubTotal(){
        return preco * quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemCompra)) return false;
        ItemCompra that = (ItemCompra) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
