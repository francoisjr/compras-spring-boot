package com.compras.spring.boot.entities;

import com.compras.spring.boot.entities.enums.CategoriaProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valor;
    private Integer categoriaProduto;
    private boolean ehParaMaior;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id.produto")
    private Set<ItemCompra> items = new HashSet<>();

    public Produto() {
    }

    public Produto(Long id, String nome, Double valor, CategoriaProduto categoriaProduto, boolean ehParaMaior) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        setCategoriaProduto(categoriaProduto);
        this.ehParaMaior = ehParaMaior;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public CategoriaProduto getCategoriaProduto() {
        return CategoriaProduto.valueOf(categoriaProduto);
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        if (categoriaProduto != null) {
            this.categoriaProduto = categoriaProduto.getCode();
        }
    }

    public boolean isEhParaMaior() {
        return ehParaMaior;
    }

    public void setEhParaMaior(boolean ehParaMaior) {
        this.ehParaMaior = ehParaMaior;
    }

    @JsonIgnore
    public Set<Compra> getCompras(){
        Set<Compra> set = new HashSet<>();
        for (ItemCompra itemCompra : items){
            set.add(itemCompra.getCompra());
        }
        return set;
    }
    @JsonIgnore
    public Set<ItemCompra> getItems() {
        return items;
    }

    public void setItems(Set<ItemCompra> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(getId(), produto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
