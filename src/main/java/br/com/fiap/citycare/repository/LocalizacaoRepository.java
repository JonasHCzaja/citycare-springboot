package br.com.fiap.citycare.repository;

import br.com.fiap.citycare.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {

    @Query("SELECT l FROM Localizacao l WHERE l.estado = :estado AND l.cidade = :cidade AND l.bairro = :bairro AND l.logradouro = :logradouro AND l.numero = :numero")
    Optional<Localizacao> buscarLocalizacao(
            @Param("estado") String estado,
            @Param("cidade") String cidade,
            @Param("bairro") String bairro,
            @Param("logradouro") String logradouro,
            @Param("numero") String numero
    );
}
