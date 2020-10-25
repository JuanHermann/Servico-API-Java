package com.example.ServicoApiJava.Repositorys;

import com.example.ServicoApiJava.Models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    Estado findBySigla(@Param("sigla") String sigla);

}

