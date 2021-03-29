package com.compras.spring.boot.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_compra")
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataCompra;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id.compra")
    private Set<ItemCompra> items = new HashSet<>();

    @Transient
    private Double total;


    public Compra() {
    }

    public Compra(Long id, LocalDate dataCompra, Cliente cliente) {
        this.id = id;
        this.dataCompra = dataCompra;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ItemCompra> getItems() {
        return items;
    }

    public void setItems(Set<ItemCompra> items) {
        this.items = items;
    }


    public Double getTotal() {
        double sum = 0.0;
        for (ItemCompra itemCompra : items) {
            sum += itemCompra.getSubTotal();
        }
        return sum;
    }

   /* public Double getTotal() {
        return total;
    }*/

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compra)) return false;
        Compra compra = (Compra) o;
        return Objects.equals(getId(), compra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
