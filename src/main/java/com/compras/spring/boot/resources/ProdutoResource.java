package com.compras.spring.boot.resources;


import com.compras.spring.boot.entities.Produto;
import com.compras.spring.boot.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Api(tags = {"API - Controle de Produtos"})
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @ApiOperation(value = "Este Endpoint tem como objetivo consultar todos os produtos existentes e então utilizar o ID para atualizar o valor de um produto")
    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> list = produtoService.findAll();
        return ResponseEntity.ok().body(list);
    }

  /*  @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok().body(produto);
    }*/

    @ApiOperation(value = "Este Endpoint tem como objetivo cadastrar novos Itens/Produtos")
    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        Produto obj = produtoService.save(produto);
        URI location = URI.create("/produtos/");
        return ResponseEntity.created(location).body(obj);
    }

    @ApiOperation(value = "Este Endpoint tem como objetivo excluir itens/produtos existentes")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Este Endpoint tem como objetivo alterar o valor de um item")
    @PatchMapping(value = "{id}/atualizarValor/{valor}")
    public ResponseEntity<Produto> updateValor(@PathVariable String id,@PathVariable Double valor) {
        Produto produto = produtoService.updateValor(Long.parseLong(id),valor);
        return ResponseEntity.ok().body(produto);
    }

}