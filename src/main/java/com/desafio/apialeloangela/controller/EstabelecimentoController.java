package com.desafio.apialeloangela.controller;

import com.desafio.apialeloangela.model.Cliente;
import com.desafio.apialeloangela.model.Estabelecimento;
import com.desafio.apialeloangela.repository.ClienteRepository;
import com.desafio.apialeloangela.repository.EstabelecimentoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estabelecimento")
@CrossOrigin(origins = "", allowedHeaders = "")
public class EstabelecimentoController {
    @Autowired
    private EstabelecimentoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping
    public ResponseEntity<List<Estabelecimento>> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estabelecimento> GetById(@PathVariable long id){
        return repository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Estabelecimento>> GetByNome (@PathVariable String nome){
        return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Estabelecimento> post (@PathVariable long id, @RequestBody Estabelecimento estabelecimento) throws NotFoundException {
       //Verifica se a empresa existe
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("cliente nao encontrado"));
        cliente.addEstabelecimento(estabelecimento.getNome(), estabelecimento.getEndereco());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(estabelecimento));
    }
    //TODO - verificar pq está salvando dois ids

    @PutMapping("/{id}")
    public ResponseEntity<Estabelecimento> put (@RequestBody Estabelecimento cadastro){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cadastro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

