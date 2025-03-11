package br.com.fiap.citycare.dto;

import br.com.fiap.citycare.model.Localizacao;
import br.com.fiap.citycare.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioComLocalizacaoCadastroDTO(
        Long usuarioId,

        @NotBlank(message = "Nome do usuario e obrigatorio!")
        String nome,

        @NotBlank(message = "E-mail e obrigatorio!")
        @Email(message = "Formato invalido!")
        String email,

        @NotBlank(message = "A senha e obrigatoria!")
        @Size(min = 6, max = 20, message = "A senha de ve conter entre 6 e 20 caracteres")
        String senha,
        UsuarioRole role,
        Localizacao endereco,
        Long localizacaoId,
        String estado,
        String cidade,
        String bairro,
        String logradouro,
        String numero
) {
}
