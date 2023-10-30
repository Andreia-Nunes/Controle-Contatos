package dev.samanda.controlecontatos.service;

import dev.samanda.controlecontatos.model.Contato;
import dev.samanda.controlecontatos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public Contato findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public List<Contato> findAll(){
        return repository.findAll();
    }

    public Contato create(Contato contato){
        return repository.save(contato);
    }

    public Contato update(Contato contato, Long id){
        Contato contatoBD = this.findById(id);

        contatoBD.setContato(contato.getContato());

        return repository.save(contatoBD);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }

}
