package com.example.ServicoApiJava.Services;

import com.example.ServicoApiJava.Models.Estado;
import com.example.ServicoApiJava.Models.LogStatusServicoNfe;
import com.example.ServicoApiJava.Repositorys.EstadoRepository;
import com.example.ServicoApiJava.Repositorys.LogStatusServicoNfeRepository;
import com.example.ServicoApiJava.Enums.Status;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class LogStatusServicoNfeService {

    @Autowired(required = false)
    private LogStatusServicoNfeRepository repository;
    @Autowired(required = false)
    private EstadoRepository estadoRepository;



    @Scheduled(cron = "0 0/5 * 1/1 * ?")
    public void atualizarDados() throws IOException {
        Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
        Elements newsHeadlines = doc.select("table.tabelaListagemDados  > tbody");
        for (Element headline : newsHeadlines) {

            for (Element headlineChild : headline.children()) {
                if (headlineChild.attributes().size() > 0) {

                    LogStatusServicoNfe log = new LogStatusServicoNfe();
                    //log.setAutorizador(headlineChild.child(0).toString().replace("</td>", "").replace("<td>", ""));
                    log.setEstado(getEstado(headlineChild.child(0).toString()));
                    log.setDataAtualizacao( LocalDateTime.now());
                    log.setAutorizacao4(getRespectivoStatus(headlineChild.child(1).toString()));
                    log.setRetornoAutorizacao4(getRespectivoStatus(headlineChild.child(2).toString()));
                    log.setInutilizacao4(getRespectivoStatus(headlineChild.child(3).toString()));
                    log.setConsultaProtocolo4(getRespectivoStatus(headlineChild.child(4).toString()));
                    log.setStatusServico4(getRespectivoStatus(headlineChild.child(5).toString()));
                    log.setTempoMedio(getRespectivoStatus(headlineChild.child(6).toString()));
                    log.setConsultaCadastro4(getRespectivoStatus(headlineChild.child(7).toString()));
                    log.setRecepcaoEvento4(getRespectivoStatus(headlineChild.child(8).toString()));

                    repository.save(log);

                }
            }
        }

    }

    public int getRespectivoStatus(String row) {
    row = row.replace("\"","");
        if (row.equals("<td><img src=imagens/bola_verde_P.png></td>")) {
            return Status.verde.valorStatus;
        } else if (row.equals("<td><img src=imagens/bola_amarela_P.png></td>")) {
            return Status.amarelo.valorStatus;
        } else if (row.equals("<td><img src=imagens/bola_vermelho_P.png></td>")) {
            return Status.vermelho.valorStatus;
        }
        return Status.semStatus.valorStatus;
    }

    public Estado getEstado(String row) {
        Estado e = new Estado();
        String sigla = row.replace("</td>", "").replace("<td>", "");

        if ( estadoRepository.findBySigla(sigla) == null ){
            e.setSigla(sigla);
            estadoRepository.save(e);
            return e;
        }else{
        return estadoRepository.findBySigla(sigla) ;
        }
    }
}
