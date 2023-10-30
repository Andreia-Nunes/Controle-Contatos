package dev.samanda.controlecontatos.controller;

import dev.samanda.controlecontatos.model.Contato;
import dev.samanda.controlecontatos.model.dtos.ContatoAtualizacaoDto;
import dev.samanda.controlecontatos.model.dtos.ContatoCriacaoDto;
import dev.samanda.controlecontatos.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

    @Operation(summary = "Obter Contato por ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contato> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @Operation(summary = "Listar todos os Contatos")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Contato>> findAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }

    @Operation(summary = "Adicionar um novo Contato a uma Pessoa")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Contato> create(@RequestBody @Valid ContatoCriacaoDto contato){
        return ResponseEntity.status(201).body(service.create(contato));
    }

    @Operation(summary = "Atualizar Contato por ID")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contato> update(@RequestBody ContatoAtualizacaoDto contato, @PathVariable Long id){
        return ResponseEntity.status(200).body(service.update(contato, id));
    }

    @Operation(summary = "Deletar Contato por ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
