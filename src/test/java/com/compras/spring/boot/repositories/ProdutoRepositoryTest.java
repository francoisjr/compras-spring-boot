package com.compras.spring.boot.repositories;

import com.compras.spring.boot.entities.Cliente;
import com.compras.spring.boot.entities.Produto;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;
    private Produto produto;

    @Test
    @Rollback(value = false)
    @Order(1)
    public void salvarProdutoTest() {
        produto = new Produto();
        produto.setValor(100.0);
        produto.setNome("Camiseta");
        produtoRepository.save(produto);
        assertThat(produto.getId()).isNotNull();
        assertThat(produto.getValor()).isEqualTo(100.0);
    }

    @Test
    @Rollback(false)
    @Order(2)
    public void alterarValorProdutoTest() {
        Optional<Produto> p = produtoRepository.findById(1L);
        p.get().setValor(200.0);
        produtoRepository.save(p.get());
        assertThat(p.get().getValor()).isEqualTo(200.0);
    }

    @Test
    @Rollback(false)
    @Order(3)
    public void deletarProdutoTest() {
        Optional<Produto> p = produtoRepository.findById(1L);
        Optional<Produto> produtoDeletado = produtoRepository.findById(1L);
        assertThat(produtoDeletado).isEmpty();
    }


}
