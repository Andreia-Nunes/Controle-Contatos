package dev.samanda.controlecontatos.model.dtos;

import dev.samanda.controlecontatos.model.Pessoa;

public record PessoaMalaDireta(
        Long id,
        String nome,
        String malaDireta
) {

    public PessoaMalaDireta(Pessoa pessoa){
        this(
                pessoa.getId(),
                pessoa.getNome(),
                String.format("%s - CEP: %s - %s/%s", pessoa.getEndereco(), pessoa.getCep(), pessoa.getCidade(), pessoa.getUf())
        );
    }
}
