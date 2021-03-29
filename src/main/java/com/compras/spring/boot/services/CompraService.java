package com.compras.spring.boot.services;

import com.compras.spring.boot.entities.*;
import com.compras.spring.boot.repositories.ClienteRepository;
import com.compras.spring.boot.repositories.CompraRepository;
import com.compras.spring.boot.repositories.ItemCompraRepository;
import com.compras.spring.boot.repositories.ProdutoRepository;
import com.compras.spring.boot.services.exceptions.ClienteNaoEncontradoException;
import com.compras.spring.boot.services.exceptions.ProdutoNaoEncontradoException;
import com.compras.spring.boot.services.exceptions.ProdutoProibidoException;
import com.compras.spring.boot.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemCompraRepository itemCompraRepository;

    public List<Compra> findAll() {
        return compraRepository.findAll();
    }

    public Compra findById(Long id) {
        Optional<Compra> obj = compraRepository.findById(id);
        return obj.get();
    }

    public List<Compra> findByData(LocalDate data) {
        List<Compra> compras = compraRepository.findByData(data);
        return compras;
    }

    public List<Compra> findByCpf(String cpf) {
        List<Compra> compras = compraRepository.findByCpf(cpf);
        return compras;
    }

    public Compra save(Compra compra) {
        return compraRepository.save(compra);
    }

    public void insertNovaCompra(NovaCompra novaCompra) {

        Optional<Cliente> cliente = clienteRepository.findByEmail(novaCompra.getEmail());
        cliente.orElseThrow(() -> new ClienteNaoEncontradoException(novaCompra.getEmail()));

        Compra compra = new Compra();
        Set<ItemCompra> mySet = new HashSet<>();

        Optional<Produto> produto = produtoRepository.findById(Long.parseLong(novaCompra.getProduto()));
        produto.orElseThrow(() -> new ProdutoNaoEncontradoException(Long.parseLong(novaCompra.getProduto())));
        int idade = Util.idade(Util.parseData(cliente.get().getDataNascimento()));
        if (idade < 18 && produto.get().isEhParaMaior()) {
            throw new ProdutoProibidoException(idade);
        }

        ItemCompra itemCompra = new ItemCompra();
        itemCompra.setCompra(compra);
        itemCompra.setQuantidade(novaCompra.getQuantidade());
        itemCompra.setPreco(Util.aplicarDesconto(produto.get().getValor(), produto.get().getCategoriaProduto().getCode()));
        itemCompra.setProduto(produto.get());
        itemCompra.setCompra(compraRepository.save(compra));
        mySet.add(itemCompra);
        itemCompraRepository.save(itemCompra);

        compra.setDataCompra(LocalDate.now());
        compra.setCliente(cliente.get());
        //compra = compraRepository.save(compra);
        compra.setItems(mySet);

    }
}