package dev.samanda.controlecontatos.controller;

import dev.samanda.controlecontatos.model.Contato;
import dev.samanda.controlecontatos.service.ContatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    private final ContatoService service;

    public ContatoController(ContatoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contato> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Contato>> findAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Contato> create(@RequestBody Contato contato){
        return ResponseEntity.status(201).body(service.create(contato));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contato> update(@RequestBody Contato contato, @PathVariable Long id){
        return ResponseEntity.status(200).body(service.update(contato, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
