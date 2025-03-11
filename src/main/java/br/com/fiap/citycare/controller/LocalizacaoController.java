package br.com.fiap.citycare.controller;

import br.com.fiap.citycare.dto.LocalizacaoCadastroDTO;
import br.com.fiap.citycare.dto.LocalizacaoExibicaoDTO;
import br.com.fiap.citycare.service.LocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @PostMapping("/localizacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public LocalizacaoExibicaoDTO salvar(@RequestBody LocalizacaoCadastroDTO localizacaoCadastroDTO){
        return localizacaoService.salvar(localizacaoCadastroDTO);
    }

    @GetMapping("/localizacoes")
    @ResponseStatus(HttpStatus.OK)
    public List<LocalizacaoExibicaoDTO> listarTodos(){
        return localizacaoService.listarTodos();
    }

    @DeleteMapping("/localizacoes/{localizacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long localizacaoId){
        localizacaoService.excluir(localizacaoId);
    }

}
