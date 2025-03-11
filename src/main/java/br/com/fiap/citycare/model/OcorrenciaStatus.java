package br.com.fiap.citycare.model;

public enum OcorrenciaStatus {
    PENDENTE("pendente"),
    EM_ANDAMENTO("em andamento"),
    RESOLVIDO("resolvido"),
    CANCELADO("cancelado");

    private String statusOcorrencia;

    OcorrenciaStatus(String statusOcorrencia){
        this.statusOcorrencia = statusOcorrencia;
    }

    public String getStatusOcorrencia(){
        return statusOcorrencia;
    }
}
