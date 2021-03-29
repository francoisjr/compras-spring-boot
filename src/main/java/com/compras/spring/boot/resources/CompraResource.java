package com.compras.spring.boot.resources;


import com.compras.spring.boot.entities.Compra;
import com.compras.spring.boot.entities.NovaCompra;
import com.compras.spring.boot.entities.dto.CompraDTO;
import com.compras.spring.boot.services.CompraService;
import com.compras.spring.boot.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Api(tags = {"API - Controle de Compras"})
@RestController
@RequestMapping(value = "/compras")
public class CompraResource {

    @Autowired
    private CompraService compraService;

    /*@GetMapping
    public ResponseEntity<List<Compra>> findAll() {
        List<Compra> list = compraService.findAll();
        return ResponseEntity.ok().body(list);
    }*/

  /*  @GetMapping(value = "/{id}")
    public ResponseEntity<Compra> findById(@PathVariable Long id) {
        Compra compra = compraService.findById(id);
        return ResponseEntity.ok().body(compra);
    }*/

    /*@PostMapping
    public ResponseEntity<Compra> save(@RequestBody Compra compra) {
        Compra obj = compraService.save(compra);
        URI location = URI.create("/compras/");
        return ResponseEntity.created(location).body(obj);
    }*/

    @ApiOperation(value = "Este Endpoint tem como objetivo incluir uma nova compra para um cliente")
    @PostMapping("/comprar")
    public  ResponseEntity<Void> incluirCompra(@RequestBody NovaCompra novaCompra){
        compraService.insertNovaCompra(novaCompra);
        URI location = URI.create("/compras/");
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Este Endpoint tem como objetivo consultar as compras de um cliente a partir do CPF do cliente")
    @GetMapping(value = "/consulta/cpf/{cpf}")
    public ResponseEntity<List<Compra>> findByCpf(@PathVariable String cpf) {
        List<Compra> compras = compraService.findByCpf(cpf);
        return ResponseEntity.ok().body(compras);
    }

    @ApiOperation(value = "Este Endpoint tem como objetivo obter uma lista das compras existentes para uma data de pesquisa\n" +
            "informada(formato YYYY-MM-DD)")
    @GetMapping(value = "/consulta/{data}")
    public ResponseEntity<List<CompraDTO>> findByData(@PathVariable String data) {
        List<Compra> compras = compraService.findByData(Util.parseData(data));
        return ResponseEntity.ok().body(Util.parseCompra(compras));
    }
}
