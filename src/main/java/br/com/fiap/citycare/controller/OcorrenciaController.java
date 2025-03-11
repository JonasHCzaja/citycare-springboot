package br.com.fiap.citycare.controller;

import br.com.fiap.citycare.dto.OcorrenciaComLocalizacaoCadastroDTO;
import br.com.fiap.citycare.dto.OcorrenciaComLocalizacaoExibicaoDTO;
import br.com.fiap.citycare.dto.OcorrenciaStatusAtualizacaoDTO;
import br.com.fiap.citycare.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @PostMapping("/ocorrencias")
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaComLocalizacaoExibicaoDTO registrarOcorrencia(@RequestBody OcorrenciaComLocalizacaoCadastroDTO cadastroDTO){
        return ocorrenciaService.cadastrarOcorrenciaComLocalizacao(cadastroDTO);
    }

    @PutMapping("/ocorrencias")
    public OcorrenciaComLocalizacaoExibicaoDTO atualizar(@RequestBody OcorrenciaComLocalizacaoCadastroDTO atualizacaoDTO){
        return ocorrenciaService.atualizarOcorrencia(atualizacaoDTO);
    }

    @PutMapping("/ocorrencias/{id}")
    public OcorrenciaComLocalizacaoExibicaoDTO atualizarStatusOcorrencia(@PathVariable Long id, @RequestBody OcorrenciaStatusAtualizacaoDTO statusAtualizacaoDTO){
        return ocorrenciaService.atualizarStatus(statusAtualizacaoDTO);
    }


    @GetMapping("/ocorrencias")
    @ResponseStatus(HttpStatus.OK)
    public List<OcorrenciaComLocalizacaoExibicaoDTO> listarTodasOcorrencias(){
        return ocorrenciaService.listarTodasOcorrencias();
    }

    @GetMapping("/ocorrencias/{bairro}")
    @ResponseStatus(HttpStatus.OK)
    public List<OcorrenciaComLocalizacaoExibicaoDTO> buscarOcorrenciaPorBairro(@PathVariable String bairro){
        return ocorrenciaService.buscarOcorrenciaPorBairro(bairro);
    }

    @GetMapping(value = "/ocorrencias", params = {"dataInicio", "dataFinal"})
    @ResponseStatus(HttpStatus.OK)
    public List<OcorrenciaComLocalizacaoExibicaoDTO> buscarOcorrenciasPorPeriodo(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFinal
    ){
        if (dataInicio.isAfter(dataFinal)){
            throw new IllegalArgumentException("A data de inicio deve ser anterior a data final.");
        }
        return ocorrenciaService.listarOcorrenciasPorPeriodo(dataInicio, dataFinal);
    }

    @DeleteMapping("/ocorrencias/{ocorrenciaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long ocorrenciaId){
        ocorrenciaService.excluir(ocorrenciaId);
    }

}
