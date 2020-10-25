package com.example.ServicoApiJava.Repositorys;

import com.example.ServicoApiJava.Models.LogStatusServicoNfe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogStatusServicoNfeRepository extends JpaRepository<LogStatusServicoNfe, Long> {

    List<LogStatusServicoNfe> findTop15ByOrderByDataAtualizacaoDesc();
    List<LogStatusServicoNfe> findTop1ByEstadoSiglaOrderByDataAtualizacaoDesc(String sigla);
    List<LogStatusServicoNfe> findByEstadoId(Integer id);
    List<LogStatusServicoNfe> findByDataAtualizacaoOrderByDataAtualizacaoDesc(LocalDateTime dataAtualizacao);

    @Query("SELECT e FROM LogStatusServicoNfe e where e.dataAtualizacao >= :data and  e.dataAtualizacao < :datafinal ")
    List<LogStatusServicoNfe> findByData(@Param("data") LocalDateTime data,@Param("datafinal") LocalDateTime datafinal);


}

