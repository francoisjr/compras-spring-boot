package com.compras.spring.boot.service;

import com.compras.spring.boot.entities.NovaCompra;
import com.compras.spring.boot.repositories.ClienteRepository;
import com.compras.spring.boot.repositories.CompraRepository;
import com.compras.spring.boot.repositories.ItemCompraRepository;
import com.compras.spring.boot.repositories.ProdutoRepository;
import com.compras.spring.boot.services.ClienteService;
import com.compras.spring.boot.services.CompraService;
import com.compras.spring.boot.services.ProdutoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CompraServiceTest {

    // @Mock
    private CompraService compraService = mock(CompraService.class);

    @Mock
    private ClienteService clienteService;


    @Mock
    private ProdutoService produtoService;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Mock
    private ItemCompraRepository itemCompraRepository;

    @Test
    public void deveRegistrarNovaCompraTest() {

        NovaCompra novaCompra1 = new NovaCompra();
        novaCompra1.setEmail("maria@gmail.com");
        novaCompra1.setProduto("1");
        novaCompra1.setQuantidade(2);

        doNothing().when(compraService).insertNovaCompra(novaCompra1);
        compraService.insertNovaCompra(novaCompra1);
        verify(compraService).insertNovaCompra(novaCompra1);
    }
}