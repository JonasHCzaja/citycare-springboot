package br.com.fiap.citycare.dto;

import br.com.fiap.citycare.model.Localizacao;
import br.com.fiap.citycare.model.OcorrenciaStatus;

import java.time.LocalDate;

public record OcorrenciaComLocalizacaoCadastroDTO(
        Long ocorrenciaId,
        String ocorrencia,
        String descricao,
        LocalDate dataOcorrencia,
        OcorrenciaStatus ocorrenciaStatus,
        Localizacao localizacao,
        Long localizacaoId,
        String estado,
        String cidade,
        String bairro,
        String logradouro,
        String numero
) {
}
