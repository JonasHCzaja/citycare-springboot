package br.com.fiap.citycare.controller;

import br.com.fiap.citycare.config.security.JwtTokenService;
import br.com.fiap.citycare.dto.LoginDTO;
import br.com.fiap.citycare.dto.TokenDTO;
import br.com.fiap.citycare.dto.UsuarioComLocalizacaoCadastroDTO;
import br.com.fiap.citycare.dto.UsuarioComLocalizacaoExibicaoDTO;
import br.com.fiap.citycare.model.Usuario;
import br.com.fiap.citycare.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO){

        try {
            UsernamePasswordAuthenticationToken usernamePassword =
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.email(),
                            loginDTO.senha()
                    );

            Authentication auth = authenticationManager.authenticate(usernamePassword);


            String token = jwtTokenService.gerarToken((Usuario) auth.getPrincipal());

            return ResponseEntity.ok(new TokenDTO(token));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticacao: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioComLocalizacaoExibicaoDTO registrar(@RequestBody @Valid UsuarioComLocalizacaoCadastroDTO usuarioCadastroDTO){
        UsuarioComLocalizacaoExibicaoDTO usuarioSalvo = usuarioService.cadastrarUsuarioComLocalizacao(usuarioCadastroDTO);
        return usuarioSalvo;
    }
}
