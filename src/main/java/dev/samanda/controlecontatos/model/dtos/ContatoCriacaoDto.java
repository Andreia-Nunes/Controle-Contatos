package dev.samanda.controlecontatos.model.dtos;

public record ContatoDto(
        Integer tipoContato,
        String contato,
        Long idPessoa
) {
}
