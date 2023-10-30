package dev.samanda.controlecontatos.service;

import dev.samanda.controlecontatos.model.Contato;
import dev.samanda.controlecontatos.model.Pessoa;
import dev.samanda.controlecontatos.model.dtos.ContatoAtualizacaoDto;
import dev.samanda.controlecontatos.model.dtos.ContatoCriacaoDto;
import dev.samanda.controlecontatos.repository.ContatoRepository;
import dev.samanda.controlecontatos.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Contato create(ContatoCriacaoDto contatoCriacaoDto){
        this.validaTipoContato(contatoCriacaoDto.tipoContato());

        Pessoa pessoa = this.buscaPessoa(contatoCriacaoDto.idPessoa());

        Contato contato = new Contato();
        contato.setTipoContato(contatoCriacaoDto.tipoContato());
        contato.setContato(contatoCriacaoDto.contato());
        contato.setPessoa(pessoa);

        return contatoRepository.save(contato);
    }

    public Contato update(ContatoAtualizacaoDto contato, Long id){
        Contato contatoBD = this.findById(id);

        contatoBD.setContato(contato.contato());

        return contatoRepository.save(contatoBD);
    }

    public void delete(Long id){
        contatoRepository.deleteById(id);
    }

    private void validaTipoContato(Integer tipoContato) {
        if(tipoContato != 0 && tipoContato != 1){
            throw new IllegalArgumentException("Tipo de contato inválido.\nValores aceitos:\n0- Telefone\n1- Celular");
        }
    }
    private Pessoa buscaPessoa(Long id){
        return pessoaRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("A pessoa informada não existe. Por favor, cadastre-a antes do contato."));
    }

}
