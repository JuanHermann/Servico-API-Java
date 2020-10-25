package com.example.ServicoApiJava.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Estado")
public class Estado  implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String sigla;

    public Estado() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
