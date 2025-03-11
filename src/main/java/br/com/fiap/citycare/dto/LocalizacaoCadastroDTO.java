package br.com.fiap.citycare.dto;

public record LocalizacaoCadastroDTO(
        Long localizacaoId,
        String estado,
        String cidade,
        String bairro,
        String logradouro,
        String numero
) {
}
