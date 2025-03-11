package br.com.fiap.citycare.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Entity
@Table(name = "tbl_ctc_ocorrencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Ocorrencia {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_CTC_OCORRENCIA"
    )
    @SequenceGenerator(
            name = "SEQ_CTC_OCORRENCIA",
            sequenceName = "SEQ_CTC_OCORRENCIA",
            allocationSize = 1
    )
    @Column(name = "ocorrencia_id")
    private Long ocorrenciaId;

    private String ocorrencia;
    private String descricao;

    @Column(name = "dt_ocorrencia")
    private LocalDate dataOcorrencia;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OcorrenciaStatus ocorrenciaStatus;

    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    @Column(name = "dt_resolucao")
    private LocalDate dataResolucao;
}
