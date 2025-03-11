package br.com.fiap.citycare.dto;

import br.com.fiap.citycare.model.Localizacao;

public record LocalizacaoExibicaoDTO(
        Long localizacaoId,
        String estado,
        String cidade,
        String bairro,
        String logradouro,
        String numero
) {
    public LocalizacaoExibicaoDTO(Localizacao localizacao){
        this(
                localizacao.getLocalizacaoId(),
                localizacao.getEstado(),
                localizacao.getCidade(),
                localizacao.getBairro(),
                localizacao.getLogradouro(),
                localizacao.getNumero()
        );
    }
}
