package dev.samanda.controlecontatos.service;

import dev.samanda.controlecontatos.model.Contato;
import dev.samanda.controlecontatos.model.Pessoa;
import dev.samanda.controlecontatos.model.dtos.ContatoCriacaoDto;
import dev.samanda.controlecontatos.repository.ContatoRepository;
import dev.samanda.controlecontatos.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    private final PessoaRepository pessoaRepository;

    public ContatoService(ContatoRepository contatoRepository, PessoaRepository pessoaRepository) {
        this.contatoRepository = contatoRepository;
        this.pessoaRepository = pessoaRepository;
    }


    public Contato findById(Long id){
        return contatoRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public List<Contato> findAll(){
        return contatoRepository.findAll();
    }

    public Contato create(ContatoCriacaoDto contatoDto){
        Pessoa pessoa = pessoaRepository.findById(contatoDto.idPessoa()).orElseThrow(() ->
                new IllegalArgumentException("A pessoa informada não existe. Por favor, cadastre-a antes do contato."));

        Contato contato = new Contato();
        contato.setTipoContato(contatoDto.tipoContato());
        contato.setContato(contatoDto.contato());
        contato.setPessoa(pessoa);

        return contatoRepository.save(contato);
    }

    public Contato update(Contato contato, Long id){
        Contato contatoBD = this.findById(id);

        contatoBD.setContato(contato.getContato());

        return contatoRepository.save(contatoBD);
    }
    public void delete(Long id){
        contatoRepository.deleteById(id);
    }

}
