package com.compras.spring.boot.repositories;

import com.compras.spring.boot.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Modifying
    @Query(value = "UPDATE Produto p SET p.valor =:valor WHERE p.id =:id")
    void updateValor(@Param("id") Long id, @Param("valor") Double valor);
}