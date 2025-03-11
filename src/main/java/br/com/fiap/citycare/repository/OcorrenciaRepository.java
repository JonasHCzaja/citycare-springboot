package br.com.fiap.citycare.repository;

import br.com.fiap.citycare.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

    @Query("SELECT o FROM Ocorrencia o WHERE o.localizacao.bairro = :bairro ORDER BY o.dataOcorrencia DESC")
    List<Ocorrencia> filtrarPorBairro(@Param("bairro") String bairro);


    List<Ocorrencia> findByDataOcorrenciaBetween(LocalDate dataInicio, LocalDate dataFinal);

}
