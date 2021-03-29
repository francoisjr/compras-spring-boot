package com.compras.spring.boot.config;

import com.compras.spring.boot.entities.*;
import com.compras.spring.boot.entities.dto.CompraDTO;
import com.compras.spring.boot.entities.enums.CategoriaProduto;
import com.compras.spring.boot.repositories.ClienteRepository;
import com.compras.spring.boot.repositories.CompraRepository;
import com.compras.spring.boot.repositories.ItemCompraRepository;
import com.compras.spring.boot.repositories.ProdutoRepository;
import com.compras.spring.boot.services.CompraService;
import com.compras.spring.boot.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("dev")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @Autowired
    private CompraService compraService;

    @Override
    public void run(String... args) throws Exception {

        Cliente cliente = new Cliente(null, "Maria", "123456788", "maria@gmail.com",
                LocalDate.parse("2000/07/22", DateTimeFormatter.ofPattern("yyyy/MM/dd")));

      /*  Cliente cliente2 = new Cliente(null, "Joao", "78945612", "joao@gmail.com",
                LocalDate.parse("2019/07/22", DateTimeFormatter.ofPattern("yyyy/MM/dd")));*/

       clienteRepository.saveAll(Arrays.asList(cliente));


        Produto produto1 = new Produto(null, "Tenis", 135.50, CategoriaProduto.PRIMARIO, false);
        Produto produto2 = new Produto(null, "Camiseta", 99.99, CategoriaProduto.SECUNDARIO, false);
        Produto produto3 = new Produto(null, "Bebida", 100.00, CategoriaProduto.PRIMARIO, true);
        Produto produto4 = new Produto(null, "Cigarro", 20.00, CategoriaProduto.SECUNDARIO, true);
        Produto produto5 = new Produto(null, "Bone", 70.00, CategoriaProduto.PRIMARIO, false);


        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5));


        Compra compra1 = new Compra(null, LocalDate.parse("2021-03-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")), cliente);
        Compra compra2 = new Compra(null, LocalDate.parse("2021-01-14", DateTimeFormatter.ofPattern("yyyy-MM-dd")), cliente);
        Compra compra3 = new Compra(null, LocalDate.parse("2019-12-30", DateTimeFormatter.ofPattern("yyyy-MM-dd")), cliente);


         compraRepository.saveAll(Arrays.asList(compra1, compra2, compra3));

        ItemCompra itemCompra1 = new ItemCompra(compra1, produto1, 2);
        ItemCompra itemCompra2 = new ItemCompra(compra2, produto3, 1);
        ItemCompra itemCompra3 = new ItemCompra(compra2, produto4, 2);
        ItemCompra itemCompra4 = new ItemCompra(compra3, produto5, 2);

        itemCompraRepository.saveAll(Arrays.asList(itemCompra1, itemCompra2, itemCompra3, itemCompra4));

        NovaCompra novaCompra1 = new NovaCompra("maria@gmail.com", "3", 2);
        NovaCompra novaCompra2 = new NovaCompra("maria@gmail.com", "4", 1);
        NovaCompra novaCompra3 = new NovaCompra("maria@gmail.com", "1", 3);

       compraService.insertNovaCompra(novaCompra1);
       compraService.insertNovaCompra(novaCompra2);
        compraService.insertNovaCompra(novaCompra3);


        List<Compra> compras = compraService.findByData(Util.parseData("2021-03-27"));

        List<CompraDTO> comprasDTO = Util.parseCompra(compras);

    }
}