package br.com.fiap.citycare.service;

import br.com.fiap.citycare.dto.LocalizacaoCadastroDTO;
import br.com.fiap.citycare.dto.OcorrenciaComLocalizacaoCadastroDTO;
import br.com.fiap.citycare.dto.OcorrenciaComLocalizacaoExibicaoDTO;
import br.com.fiap.citycare.dto.OcorrenciaStatusAtualizacaoDTO;
import br.com.fiap.citycare.exception.OcorrenciaNaoEncontradaException;
import br.com.fiap.citycare.model.Localizacao;
import br.com.fiap.citycare.model.Ocorrencia;
import br.com.fiap.citycare.model.OcorrenciaStatus;
import br.com.fiap.citycare.repository.LocalizacaoRepository;
import br.com.fiap.citycare.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private LocalizacaoService localizacaoService;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;


    public OcorrenciaComLocalizacaoExibicaoDTO cadastrarOcorrenciaComLocalizacao(OcorrenciaComLocalizacaoCadastroDTO cadastroDTO){

        LocalizacaoCadastroDTO localizacaoCadastroDTO = new LocalizacaoCadastroDTO(
                cadastroDTO.localizacaoId(),
                cadastroDTO.estado(),
                cadastroDTO.cidade(),
                cadastroDTO.bairro(),
                cadastroDTO.logradouro(),
                cadastroDTO.numero()
        );

        Localizacao endereco = localizacaoService
                .buscarOuSalvarLocalizacao(localizacaoCadastroDTO);

        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setOcorrencia(cadastroDTO.ocorrencia());
        ocorrencia.setDescricao(cadastroDTO.descricao());
        ocorrencia.setDataOcorrencia(cadastroDTO.dataOcorrencia());
        ocorrencia.setOcorrenciaStatus(cadastroDTO.ocorrenciaStatus());
        ocorrencia.setLocalizacao(endereco);

        return new OcorrenciaComLocalizacaoExibicaoDTO(ocorrenciaRepository.save(ocorrencia));

    }


    public OcorrenciaComLocalizacaoExibicaoDTO atualizarOcorrencia(OcorrenciaComLocalizacaoCadastroDTO atualizacaoDTO){

        Ocorrencia ocorrencia  = ocorrenciaRepository.findById(atualizacaoDTO.ocorrenciaId())
                .orElseThrow(() -> new OcorrenciaNaoEncontradaException("Ocorrencia nao encontrada!"));


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


        ocorrencia.setOcorrencia(atualizacaoDTO.ocorrencia());
        ocorrencia.setDescricao(atualizacaoDTO.descricao());
        ocorrencia.setDataOcorrencia(atualizacaoDTO.dataOcorrencia());
        ocorrencia.setOcorrenciaStatus(atualizacaoDTO.ocorrenciaStatus());
        ocorrencia.setLocalizacao(endereco);

        return new OcorrenciaComLocalizacaoExibicaoDTO(ocorrenciaRepository.save(ocorrencia));


    }


    public OcorrenciaComLocalizacaoExibicaoDTO atualizarStatus(OcorrenciaStatusAtualizacaoDTO statusAtualizacaoDTO){

        Optional<Ocorrencia> ocorrenciaOptional = ocorrenciaRepository.findById(statusAtualizacaoDTO.ocorrenciaId());


        if (ocorrenciaOptional.isPresent()){
            Ocorrencia ocorrencia = ocorrenciaOptional.get();

            ocorrencia.setOcorrenciaStatus(statusAtualizacaoDTO.ocorrenciaStatus());
            if (ocorrencia.getOcorrenciaStatus() == OcorrenciaStatus.RESOLVIDO){
                ocorrencia.setDataResolucao(LocalDate.now());
            }

//            Optional<Localizacao> localizacaoOptional = localizacaoRepository.findById(ocorrencia.getLocalizacao().getLocalizacaoId());
//            Localizacao localizacao = localizacaoOptional.get();


            return new OcorrenciaComLocalizacaoExibicaoDTO(ocorrenciaRepository.save(ocorrencia));
        } else {
            throw new OcorrenciaNaoEncontradaException("Ocorrencia nao encontrada!");
        }


    }

    public List<OcorrenciaComLocalizacaoExibicaoDTO> listarTodasOcorrencias(){
        return ocorrenciaRepository
                .findAll()
                .stream()
                .map(OcorrenciaComLocalizacaoExibicaoDTO::new)
                .toList();
    }


    public List<OcorrenciaComLocalizacaoExibicaoDTO> buscarOcorrenciaPorBairro(String bairro){
        return ocorrenciaRepository
                .filtrarPorBairro(bairro)
                .stream()
                .map(OcorrenciaComLocalizacaoExibicaoDTO::new)
                .toList();

    }


    public List<OcorrenciaComLocalizacaoExibicaoDTO> listarOcorrenciasPorPeriodo(LocalDate dataInicio, LocalDate dataFinal){
        return ocorrenciaRepository
                .findByDataOcorrenciaBetween(dataInicio, dataFinal)
                .stream()
                .map(OcorrenciaComLocalizacaoExibicaoDTO::new)
                .toList();
    }


    public void excluir(Long id){
        Optional<Ocorrencia> ocorrenciaOptional = ocorrenciaRepository.findById(id);

        if (ocorrenciaOptional.isPresent()){
            ocorrenciaRepository.delete(ocorrenciaOptional.get());
        } else {
            throw new OcorrenciaNaoEncontradaException("Ocorrencia nao encontrada!");
        }
    }
}
