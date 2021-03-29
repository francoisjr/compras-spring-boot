package com.compras.spring.boot.repositories;

import com.compras.spring.boot.entities.Cliente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;
    private Cliente cliente;

    @Before
    public void init() {
       // clienteRepository.deleteAll();
    }

    @After
    public void setup() {
        //clienteRepository.deleteAll();
    }

    @Test
    @Rollback(value = false)
    @Order(1)
    public void salvarClienteTest() {
        cliente = new Cliente();
        cliente.setNome("Francisco");
        cliente.setCpf("123456789");
        cliente.setEmail("francis@gmail.com");
        cliente.setDataNascimento("2000-07-22");
        clienteRepository.save(cliente);

        assertThat(cliente.getId()).isNotNull();
        assertThat(cliente.getNome()).isEqualTo("Francisco");
        assertThat(cliente.getCpf()).isEqualTo("123456789");
        assertThat(cliente.getEmail()).isEqualTo("francis@gmail.com");
        assertThat(cliente.getDataNascimento()).isEqualTo("2000-07-22");
    }

    @Test
    @Order(2)
    public void buscarCLientePorCpfTest() {
        salvarClienteTest();
        cliente = clienteRepository.findByCpf("123456789");
        assertThat(cliente.getCpf()).isEqualTo("123456789");
    }

    @Test
    @Order(3)
    public void buscarCLientePorEmailTest() {
        Optional<Cliente> cliente = clienteRepository.findByEmail("francis@gmail.com");

        assertThat(cliente.get().getEmail()).isEqualTo("francis@gmail.com");
    }

    @Test
    @Order(4)
    public void ListarClientesTest() {
        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).size().isGreaterThan(0);
    }

    @Test
    @Rollback(false)
    @Order(5)
    public void AlterarNomeClienteTest() {
        Optional<Cliente> cliente = clienteRepository.findByEmail("francis@gmail.com");
        cliente.get().setNome("Francisco Atualizado");
        clienteRepository.save(cliente.get());
        assertThat(cliente.get().getNome()).isEqualTo("Francisco Atualizado");
    }


}