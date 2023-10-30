package dev.samanda.controlecontatos.controller;

import dev.samanda.controlecontatos.model.Pessoa;
import dev.samanda.controlecontatos.model.dtos.PessoaMalaDireta;
import dev.samanda.controlecontatos.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pessoa> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @GetMapping("/mala-direta/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PessoaMalaDireta> findMalaDireta(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findMalaDireta(id));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Pessoa>> findAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa){
        return ResponseEntity.status(201).body(service.create(pessoa));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa, @PathVariable Long id){
        return ResponseEntity.status(200).body(service.update(pessoa, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}
