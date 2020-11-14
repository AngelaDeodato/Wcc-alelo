package com.desafio.apialeloangela.model;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cadastro_Cliente")
@Data
@Getter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max= 100)
    private String nomeCompleto;

    @NotNull
    private String email;

    private Long pontos = 0L;

    @NotNull
    @Size(max= 11)
    private String cpf;

    @OneToMany(mappedBy = "cliente", cascade = {CascadeType.ALL, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Estabelecimento> empresas = new ArrayList<>();


    public Cliente(long id, @Size(max = 100) String nomeCompleto, String email, Long pontos, @Size(max = 11) String cpf,
                   List<Estabelecimento> empresas) {
        super();
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.pontos = pontos;
        this.cpf = cpf;
        this.empresas = empresas;
    }

    public Estabelecimento addEstabelecimento(String nome, String endereco ){
        Estabelecimento estabelecimento = new Estabelecimento(nome, endereco, this);
        empresas.add(estabelecimento);
        addPontos();
        return estabelecimento;
    }

    public Cliente() {
    }

    private void addPontos() {
        this.pontos +=2L;
    }

    public void removePontos(Long pontos){
        this.pontos -= pontos;
    }


}
