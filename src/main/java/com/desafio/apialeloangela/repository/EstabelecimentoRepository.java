package com.desafio.apialeloangela.repository;

import java.util.List;

import com.desafio.apialeloangela.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
    List<Estabelecimento> findAllByNomeContainingIgnoreCase (String nome);
}