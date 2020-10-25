
package com.example.ServicoApiJava.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "LogStatusServicoNfe")
public class LogStatusServicoNfe implements Serializable {



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataAtualizacao;

    private int  autorizacao4;
    private int  retornoAutorizacao4;
    private int  inutilizacao4;
    private int  consultaProtocolo4;
    private int  statusServico4;
    private int  tempoMedio;
    private int  consultaCadastro4;
    private int  recepcaoEvento4;

    @OneToOne(targetEntity=Estado.class, fetch=FetchType.EAGER)
    @JoinColumn(name="estadoId")
    private Estado estado;


    public LogStatusServicoNfe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public int getAutorizacao4() {
        return autorizacao4;
    }

    public void setAutorizacao4(int autorizacao4) {
        this.autorizacao4 = autorizacao4;
    }

    public int getRetornoAutorizacao4() {
        return retornoAutorizacao4;
    }

    public void setRetornoAutorizacao4(int retornoAutorizacao4) {
        this.retornoAutorizacao4 = retornoAutorizacao4;
    }

    public int getInutilizacao4() {
        return inutilizacao4;
    }

    public void setInutilizacao4(int inutilizacao4) {
        this.inutilizacao4 = inutilizacao4;
    }

    public int getConsultaProtocolo4() {
        return consultaProtocolo4;
    }

    public void setConsultaProtocolo4(int consultaProtocolo4) {
        this.consultaProtocolo4 = consultaProtocolo4;
    }

    public int getStatusServico4() {
        return statusServico4;
    }

    public void setStatusServico4(int statusServico4) {
        this.statusServico4 = statusServico4;
    }

    public int getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(int tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public int getConsultaCadastro4() {
        return consultaCadastro4;
    }

    public void setConsultaCadastro4(int consultaCadastro4) {
        this.consultaCadastro4 = consultaCadastro4;
    }

    public int getRecepcaoEvento4() {
        return recepcaoEvento4;
    }

    public void setRecepcaoEvento4(int recepcaoEvento4) {
        this.recepcaoEvento4 = recepcaoEvento4;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}



