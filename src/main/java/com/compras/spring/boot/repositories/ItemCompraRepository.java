package com.compras.spring.boot.repositories;

import com.compras.spring.boot.entities.ItemCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long> {

   /* @Query(value = "SELECT SUM((preco * quantidade)) AS VALOR_TOTAL FROM TB_ITEM_COMPRA item_compra inner join TB_COMPRA compra on item_compra.compra_id = compra.id \n" +
            "WHERE compra.data_compra= :dataCompra", nativeQuery = true)
    Double getTotalDoDia(LocalDate dataCompra);*/

}