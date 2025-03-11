package br.com.fiap.citycare.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tbl_ctc_localizacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Localizacao {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_CTC_LOCALIZACAO"
    )
    @SequenceGenerator(
            name = "SEQ_CTC_LOCALIZACAO",
            sequenceName = "SEQ_CTC_LOCALIZACAO",
            allocationSize = 1
    )
    @Column(name = "localizacao_id")
    private Long localizacaoId;

    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String numero;
}