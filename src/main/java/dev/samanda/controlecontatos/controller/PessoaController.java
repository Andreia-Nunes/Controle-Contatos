package dev.samanda.controlecontatos.controller;

import dev.samanda.controlecontatos.model.Pessoa;
import dev.samanda.controlecontatos.model.dtos.PessoaMalaDireta;
import dev.samanda.controlecontatos.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

    @Operation(summary = "Obter Pessoa por ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pessoa> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @Operation(summary = "Obter Pessoa por ID para mala direta")
    @GetMapping("/{id}/mala-direta")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PessoaMalaDireta> findMalaDireta(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findMalaDireta(id));
    }

    @Operation(summary = "Listar todas as Pessoas")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Pessoa>> findAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }

    @Operation(summary = "Criar Pessoa")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pessoa> create(@RequestBody @Valid Pessoa pessoa){
        return ResponseEntity.status(201).body(service.create(pessoa));
    }

    @Operation(summary = "Atualizar Pessoa por ID")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pessoa> update(@RequestBody @Valid Pessoa pessoa, @PathVariable Long id){
        return ResponseEntity.status(200).body(service.update(pessoa, id));
    }

    @Operation(summary = " Deletar Pessoa por ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
