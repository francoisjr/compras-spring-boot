package com.compras.spring.boot.repositories;

import com.compras.spring.boot.entities.Cliente;
import com.compras.spring.boot.entities.Compra;
import com.compras.spring.boot.entities.ItemCompra;
import com.compras.spring.boot.entities.Produto;
import com.compras.spring.boot.entities.enums.CategoriaProduto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraRepositoryTest {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemCompraRepository itemCompraRepository;
    private Compra compra;

    @Before
    public void init() {
        Cliente cliente = new Cliente(null, "Maria", "123456788", "maria@gmail.com",
                LocalDate.parse("2000/07/22", DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        Cliente cliente2 = new Cliente(null, "Joao", "78945612", "joao@gmail.com",
                LocalDate.parse("2019/07/22", DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        clienteRepository.saveAll(Arrays.asList(cliente, cliente2));


        Produto produto1 = new Produto(null, "Tenis", 135.50, CategoriaProduto.PRIMARIO, false);
        Produto produto2 = new Produto(null, "Camiseta", 99.99, CategoriaProduto.SECUNDARIO, false);
        Produto produto3 = new Produto(null, "Bebida", 100.00, CategoriaProduto.PRIMARIO, true);
        Produto produto4 = new Produto(null, "Cigarro", 20.00, CategoriaProduto.SECUNDARIO, true);
        Produto produto5 = new Produto(null, "Bone", 70.00, CategoriaProduto.PRIMARIO, false);


        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5));


        Compra compra1 = new Compra(null, LocalDate.parse("2021-03-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")), cliente);
        Compra compra2 = new Compra(null, LocalDate.parse("2021-01-14", DateTimeFormatter.ofPattern("yyyy-MM-dd")), cliente2);
        Compra compra3 = new Compra(null, LocalDate.parse("2019-12-30", DateTimeFormatter.ofPattern("yyyy-MM-dd")), cliente2);


        compraRepository.saveAll(Arrays.asList(compra1, compra2, compra3));

        ItemCompra itemCompra1 = new ItemCompra(compra1, produto1, 2);
        ItemCompra itemCompra2 = new ItemCompra(compra2, produto3, 1);
        ItemCompra itemCompra3 = new ItemCompra(compra2, produto4, 2);
        ItemCompra itemCompra4 = new ItemCompra(compra3, produto5, 2);

        itemCompraRepository.saveAll(Arrays.asList(itemCompra1, itemCompra2, itemCompra3, itemCompra4));
    }


    @Test
    public void buscarCompraByDataTest() {
        List<Compra> compras = compraRepository.findByData(LocalDate.parse("2021-03-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertThat(compras).size().isGreaterThan(0);
    }

    @Test
    public void buscarCompraByCpfTest() {
        List<Compra> compras = compraRepository.findByCpf("123456788");
        assertThat(compras).size().isGreaterThan(0);
    }
}
