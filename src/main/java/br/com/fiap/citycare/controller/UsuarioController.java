package br.com.fiap.citycare.controller;

import br.com.fiap.citycare.dto.UsuarioComLocalizacaoCadastroDTO;
import br.com.fiap.citycare.dto.UsuarioComLocalizacaoExibicaoDTO;
import br.com.fiap.citycare.exception.UsuarioNaoEncontradoException;
import br.com.fiap.citycare.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioComLocalizacaoExibicaoDTO salvar(@RequestBody @Valid UsuarioComLocalizacaoCadastroDTO cadastroDTO){
        return usuarioService.cadastrarUsuarioComLocalizacao(cadastroDTO);
    }


    @PutMapping("/usuarios")
    public ResponseEntity<UsuarioComLocalizacaoExibicaoDTO> atualizar(@RequestBody UsuarioComLocalizacaoCadastroDTO cadastroDTO){
        try {
            UsuarioComLocalizacaoExibicaoDTO usuarioComLocalizacaoExibicaoDTO = usuarioService.atualizar(cadastroDTO);
            return ResponseEntity.ok(usuarioComLocalizacaoExibicaoDTO);
        } catch (UsuarioNaoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<UsuarioComLocalizacaoExibicaoDTO> buscarPorId(@PathVariable Long usuarioId){
        try {
            return ResponseEntity.ok(usuarioService.buscarPorId(usuarioId));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioComLocalizacaoExibicaoDTO> listarTodos(){
        return usuarioService.listarTodosUsuarios();
    }


    @DeleteMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId){
        usuarioService.excluir(usuarioId);
    }

}
