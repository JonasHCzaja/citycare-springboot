package br.com.fiap.citycare.dto;

import br.com.fiap.citycare.model.OcorrenciaStatus;

public record OcorrenciaStatusAtualizacaoDTO(
        Long ocorrenciaId,
        OcorrenciaStatus ocorrenciaStatus
) {
}
