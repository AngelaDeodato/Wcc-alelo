package com.desafio.apialeloangela.controller;

import com.desafio.apialeloangela.model.Cliente;
import com.desafio.apialeloangela.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "", allowedHeaders = "")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public ResponseEntity<List<Cliente>> GetAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> GetById(@PathVariable long id){
        return repository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<Cliente>> GetByCpf (@PathVariable String cpf){
        return ResponseEntity.ok(repository.findAllByCpfContainingIgnoreCase(cpf));
    }

    @PostMapping
    public ResponseEntity<Cliente> post (@RequestBody Cliente cadastro){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cadastro));
    }
    @PutMapping
    public ResponseEntity<Cliente> put (@RequestBody Cliente cadastro){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cadastro));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

