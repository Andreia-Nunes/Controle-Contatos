package dev.samanda.controlecontatos.model.dtos;

public record ContatoCriacaoDto(
        Integer tipoContato,
        String contato,
        Long idPessoa
) {
}
