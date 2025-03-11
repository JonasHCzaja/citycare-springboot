package br.com.fiap.citycare.dto;

import br.com.fiap.citycare.model.Usuario;
import br.com.fiap.citycare.model.UsuarioRole;

public record UsuarioComLocalizacaoExibicaoDTO(
        Long usuarioId,
        String nome,
        String email,
        UsuarioRole role,
        String estado,
        String cidade,
        String bairro,
        String logradouro,
        String numero
) {

    public UsuarioComLocalizacaoExibicaoDTO(Usuario usuario){
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.getLocalizacao().getEstado(),
                usuario.getLocalizacao().getCidade(),
                usuario.getLocalizacao().getBairro(),
                usuario.getLocalizacao().getLogradouro(),
                usuario.getLocalizacao().getNumero()
        );
    }
}
