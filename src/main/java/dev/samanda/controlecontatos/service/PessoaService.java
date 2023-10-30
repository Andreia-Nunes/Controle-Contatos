package dev.samanda.controlecontatos.service;

import dev.samanda.controlecontatos.model.Pessoa;
import dev.samanda.controlecontatos.model.dtos.PessoaMalaDireta;
import dev.samanda.controlecontatos.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Pessoa findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public PessoaMalaDireta findMalaDireta(Long id){
        Pessoa pessoaBD = this.findById(id);

        return new PessoaMalaDireta(pessoaBD);
    }

    public List<Pessoa> findAll(){
        return repository.findAll();
    }

    public Pessoa create(Pessoa pessoa){
        return repository.save(pessoa);
    }

    public Pessoa update(Pessoa pessoa, Long id){
        Pessoa pessoaBD = this.findById(id);

        pessoaBD.setNome(pessoa.getNome());
        pessoaBD.setEndereco(pessoa.getEndereco());
        pessoaBD.setCep(pessoa.getCep());
        pessoaBD.setCidade(pessoa.getCidade());
        pessoaBD.setUf(pessoa.getUf());

        return repository.save(pessoaBD);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
