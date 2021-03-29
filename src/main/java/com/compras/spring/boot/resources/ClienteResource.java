package com.compras.spring.boot.resources;


import com.compras.spring.boot.entities.Cliente;
import com.compras.spring.boot.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Api(tags = {"API - Controle de Clientes"})
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @ApiOperation(value = "Este Endpoint tem como objetivo consultar todos os clientes existentes")
    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> list = clienteService.findAll();
        return ResponseEntity.ok().body(list);
    }

    /*@GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }*/

    @ApiOperation(value = "Este Endpoint tem como objetivo consultar um cliente a partir de um E-mail")
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<Cliente> findByEmail(@PathVariable String email) {
        Cliente cliente = clienteService.findByEmail(email);
        return ResponseEntity.ok().body(cliente);
    }

    @ApiOperation(value = "Este Endpoint tem como objetivo consultar um cliente a partir de um CPF")
    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<Cliente> findByCpf(@PathVariable String cpf) {
        Cliente cliente = clienteService.findByCpf(cpf);
        return ResponseEntity.ok().body(cliente);
    }

    @ApiOperation(value = "Este Endpoint tem como objetivo cadastrar novo cliente")
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        Cliente obj = clienteService.save(cliente);
        URI location = URI.create("/clientes/");
        return ResponseEntity.created(location).body(obj);
    }

    @ApiOperation(value = "Este Endpoint tem como objetivo alterar o nome de um cliente existente")
    @PatchMapping(value = "{email}/atualizarNome/{nome}")
    public ResponseEntity<Cliente> findByCpf(@PathVariable String email, @PathVariable String nome) {
        Cliente cliente = clienteService.updateNome(email, nome);
        return ResponseEntity.ok().body(cliente);
    }

}
