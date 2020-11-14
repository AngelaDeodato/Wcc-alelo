package com.desafio.apialeloangela.repository;
import java.util.List;

import com.desafio.apialeloangela.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    List<Cliente> findAllByCpfContainingIgnoreCase(String Cpf);
}