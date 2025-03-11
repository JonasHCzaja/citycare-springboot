package br.com.fiap.citycare.service;

import br.com.fiap.citycare.dto.LocalizacaoCadastroDTO;
import br.com.fiap.citycare.dto.UsuarioComLocalizacaoCadastroDTO;
import br.com.fiap.citycare.dto.UsuarioComLocalizacaoExibicaoDTO;
import br.com.fiap.citycare.exception.UsuarioNaoEncontradoException;
import br.com.fiap.citycare.model.Localizacao;
import br.com.fiap.citycare.model.Usuario;
import br.com.fiap.citycare.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocalizacaoService localizacaoService;

    public UsuarioComLocalizacaoExibicaoDTO cadastrarUsuarioComLocalizacao(UsuarioComLocalizacaoCadastroDTO usuarioComLocalizacaoCadastroDTO){

        LocalizacaoCadastroDTO localizacaoCadastroDTO = new LocalizacaoCadastroDTO(
                usuarioComLocalizacaoCadastroDTO.localizacaoId(),
                usuarioComLocalizacaoCadastroDTO.estado(),
                usuarioComLocalizacaoCadastroDTO.cidade(),
                usuarioComLocalizacaoCadastroDTO.bairro(),
                usuarioComLocalizacaoCadastroDTO.logradouro(),
                usuarioComLocalizacaoCadastroDTO.numero()
        );

        Localizacao endereco = localizacaoService
                .buscarOuSalvarLocalizacao(localizacaoCadastroDTO);

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioComLocalizacaoCadastroDTO.senha());

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioComLocalizacaoCadastroDTO.nome());
        usuario.setEmail(usuarioComLocalizacaoCadastroDTO.email());
        usuario.setSenha(senhaCriptografada);
        usuario.setRole(usuarioComLocalizacaoCadastroDTO.role());
        usuario.setLocalizacao(endereco);



        return new UsuarioComLocalizacaoExibicaoDTO(usuarioRepository.save(usuario));


    }


    public UsuarioComLocalizacaoExibicaoDTO atualizar(UsuarioComLocalizacaoCadastroDTO atualizacaoDTO){

        Usuario usuario = usuarioRepository.findById(atualizacaoDTO.usuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado!"));


        LocalizacaoCadastroDTO localizacaoCadastroDTO = new LocalizacaoCadastroDTO(
                atualizacaoDTO.localizacaoId(),
                atualizacaoDTO.estado(),
                atualizacaoDTO.cidade(),
                atualizacaoDTO.bairro(),
                atualizacaoDTO.logradouro(),
                atualizacaoDTO.numero()
        );

        Localizacao endereco = localizacaoService
                .buscarOuSalvarLocalizacao(localizacaoCadastroDTO);

        String senhaCriptografada = new BCryptPasswordEncoder().encode(atualizacaoDTO.senha());


        usuario.setNome(atualizacaoDTO.nome());
        usuario.setEmail(atualizacaoDTO.email());
        usuario.setSenha(senhaCriptografada);
        usuario.setRole(atualizacaoDTO.role());
        usuario.setLocalizacao(endereco);



        return new UsuarioComLocalizacaoExibicaoDTO(usuarioRepository.save(usuario));

    }

    public UsuarioComLocalizacaoExibicaoDTO buscarPorId(Long id){

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            return new UsuarioComLocalizacaoExibicaoDTO(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
    }

    public List<UsuarioComLocalizacaoExibicaoDTO> listarTodosUsuarios(){
        return usuarioRepository
                .findAll()
                .stream()
                .map(UsuarioComLocalizacaoExibicaoDTO::new)
                .toList();
    }


    public void excluir(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
    }
}
