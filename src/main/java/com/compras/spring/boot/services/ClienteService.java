package com.compras.spring.boot.services;

import com.compras.spring.boot.entities.Cliente;
import com.compras.spring.boot.repositories.ClienteRepository;
import com.compras.spring.boot.services.exceptions.ClienteNaoEncontradoException;
import com.compras.spring.boot.services.exceptions.EmailDuplicadoException;
import com.compras.spring.boot.services.exceptions.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAllClientes();
    }

    public Cliente findById(Long id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new RecursoNaoEncontradoException());
    }

    public Cliente findByEmail(String email) {
        Optional<Cliente> obj = clienteRepository.findByEmail(email);
        return obj.orElseThrow(() -> new ClienteNaoEncontradoException(email));

    }

    public Cliente findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public Cliente save(Cliente cliente) {
        try {
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new EmailDuplicadoException(cliente.getEmail());
        }
    }

    @Transactional
    public Cliente updateNome(String email, String nome) {
        Optional<Cliente> obj = clienteRepository.findByEmail(email);
        obj.get().setNome(nome);
        return clienteRepository.save(obj.get());
    }

}