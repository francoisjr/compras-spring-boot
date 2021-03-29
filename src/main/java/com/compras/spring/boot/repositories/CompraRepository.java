package com.compras.spring.boot.repositories;

import com.compras.spring.boot.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query(value = "SELECT c FROM Compra c INNER JOIN ItemCompra item_compra ON\n" +
            " c.id = item_compra.id.compra WHERE c.dataCompra = ?1")
    List<Compra> findByData(LocalDate dataCompra);

    @Query(value = "SELECT c FROM Compra c INNER JOIN Cliente cliente ON c.cliente.id = cliente.id WHERE cliente.cpf= ?1")
    List<Compra> findByCpf(String cpf);
}