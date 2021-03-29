package com.compras.spring.boot.repositories;

import com.compras.spring.boot.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT * FROM tb_cliente", nativeQuery = true)
    List<Cliente> findAllClientes();

    @Query(value = "SELECT c FROM Cliente c WHERE c.email = ?1")
    Optional<Cliente> findByEmail(String email);

    @Query(value = "SELECT c FROM Cliente c WHERE c.cpf = ?1")
    Cliente findByCpf(String cpf);

    @Modifying
    @Query(value = "UPDATE Cliente c SET c.nome =:nome WHERE c.email =:email")
    void updateNome(@Param("email") String email, @Param("nome") String nome);


}