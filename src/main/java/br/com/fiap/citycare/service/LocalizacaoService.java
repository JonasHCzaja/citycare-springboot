package br.com.fiap.citycare.service;

import br.com.fiap.citycare.dto.LocalizacaoCadastroDTO;
import br.com.fiap.citycare.dto.LocalizacaoExibicaoDTO;
import br.com.fiap.citycare.model.Localizacao;
import br.com.fiap.citycare.repository.LocalizacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public LocalizacaoExibicaoDTO salvar(LocalizacaoCadastroDTO localizacaoCadastroDTO){
        Localizacao localizacao = new Localizacao();
        BeanUtils.copyProperties(localizacaoCadastroDTO, localizacao);

        Localizacao localizacaoSalva = localizacaoRepository.save(localizacao);
        return new LocalizacaoExibicaoDTO(localizacaoSalva);
    }


    public Localizacao buscarOuSalvarLocalizacao(LocalizacaoCadastroDTO localizacaoCadastroDTO){

        Optional<Localizacao> localizacaoOptional = localizacaoRepository
                .buscarLocalizacao(
                        localizacaoCadastroDTO.estado(),
                        localizacaoCadastroDTO.cidade(),
                        localizacaoCadastroDTO.bairro(),
                        localizacaoCadastroDTO.logradouro(),
                        localizacaoCadastroDTO.numero()
                );

        if (localizacaoOptional.isPresent()){
            return localizacaoOptional.get();
        } else {
            Localizacao localizacao = new Localizacao();
            BeanUtils.copyProperties(localizacaoCadastroDTO, localizacao);

            Localizacao localizacaoSalva = localizacaoRepository.save(localizacao);
            return localizacaoSalva;
        }
    }


    public List<LocalizacaoExibicaoDTO> listarTodos(){
        return localizacaoRepository
                .findAll()
                .stream()
                .map(LocalizacaoExibicaoDTO::new)
                .toList();
    }


    public void excluir(Long id){
        Optional<Localizacao> localizacaoOptional = localizacaoRepository.findById(id);

        if(localizacaoOptional.isPresent()){
            localizacaoRepository.delete(localizacaoOptional.get());
        } else {
            throw new RuntimeException("Localização não encontrada!");
        }
    }

}
