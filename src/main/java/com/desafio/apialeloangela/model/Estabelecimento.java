package com.desafio.apialeloangela.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;


import lombok.NoArgsConstructor;
@Entity
@Table(name = "Cadastro_Estabelecimento")
@NoArgsConstructor
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 3, max= 100)
    private String nome;

    @NotNull
    @Size(min = 5, max= 100)
    private String endereco;

    @CreationTimestamp
    private Date date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;



    public Estabelecimento(String nome, String endereco, Cliente cliente) {
        this.nome = nome;
        this.endereco = endereco;
        this.cliente = cliente;
    }



    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }



}
