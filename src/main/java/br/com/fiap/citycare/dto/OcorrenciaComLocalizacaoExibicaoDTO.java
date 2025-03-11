package br.com.fiap.citycare.dto;

import br.com.fiap.citycare.model.Ocorrencia;
import br.com.fiap.citycare.model.OcorrenciaStatus;

import java.time.LocalDate;

public record OcorrenciaComLocalizacaoExibicaoDTO(
        Long ocorrenciaId,
        String nomeOcorrencia,
        String descricao,
        LocalDate dataOcorrencia,
        OcorrenciaStatus ocorrenciaStatus,
        LocalDate dataResolucao,
        String estado,
        String cidade,
        String bairro,
        String logradouro,
        String numero
) {

    public OcorrenciaComLocalizacaoExibicaoDTO(Ocorrencia ocorrencia){
        this(
                ocorrencia.getOcorrenciaId(),
                ocorrencia.getOcorrencia(),
                ocorrencia.getDescricao(),
                ocorrencia.getDataOcorrencia(),
                ocorrencia.getOcorrenciaStatus(),
                ocorrencia.getDataResolucao(),
                ocorrencia.getLocalizacao().getEstado(),
                ocorrencia.getLocalizacao().getCidade(),
                ocorrencia.getLocalizacao().getBairro(),
                ocorrencia.getLocalizacao().getLogradouro(),
                ocorrencia.getLocalizacao().getNumero()
        );
    }
}
