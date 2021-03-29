package com.compras.spring.boot.services;

import com.compras.spring.boot.entities.Produto;
import com.compras.spring.boot.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.get();
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    @Transactional
    public Produto updateValor(Long id, Double valor) {
        Optional<Produto> obj = produtoRepository.findById(id);
        obj.get().setValor(valor);
        return produtoRepository.save(obj.get());
    }

}