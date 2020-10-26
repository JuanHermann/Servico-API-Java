package com.example.ServicoApiJava.Controllers;

import com.example.ServicoApiJava.Enums.Status;
import com.example.ServicoApiJava.Models.LogStatusServicoNfe;
import com.example.ServicoApiJava.Repositorys.EstadoRepository;
import com.example.ServicoApiJava.Repositorys.LogStatusServicoNfeRepository;
import com.example.ServicoApiJava.Services.LogStatusServicoNfeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/status")
public class LogStatusServicoNfeController {

    @Autowired(required = false)
    private LogStatusServicoNfeService logStatusServicoNfeService;
    @Autowired(required = false)
    private LogStatusServicoNfeRepository repository;
    @Autowired(required = false)
    private EstadoRepository estadoRepository;



    @GetMapping(value = "/forcaratualizacao")
    public void forcarAtualizacao() {
        try {
            logStatusServicoNfeService.atualizarDados();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/statusatualestado")
    public List<LogStatusServicoNfe> statusAtualEstado() {
        try {
            logStatusServicoNfeService.atualizarDados();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repository.findTop15ByOrderByDataAtualizacaoDesc();
    }

    @GetMapping(value = "/statusatualestado/{sigla}")
    public List<LogStatusServicoNfe> statusAtualEstado(@PathVariable String sigla) {
        try {
            logStatusServicoNfeService.atualizarDados();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repository.findTop1ByEstadoSiglaOrderByDataAtualizacaoDesc(sigla);
    }
    
    //Padrão yyyy-MM-dd 2020-10-26
    @GetMapping(value = "/statusatualdata/{data}")
    public List<LogStatusServicoNfe> statusAtualData( @PathVariable String data ) {

        if (data == null){
            data = LocalDateTime.now().toLocalDate().toString();
        }
        data = data.concat(" 00:00");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(data, formatter);

        return repository.findByData(date,date.plusDays(1));

    }

    @GetMapping(value = "/indisponibilidade")
    public List<LogStatusServicoNfe> indisponibilidade() {
        List<Integer> qtdFalhas = new ArrayList();

        for (int i =0; i < estadoRepository.findAll().size() + 1;i++ ){
            qtdFalhas.add(0);
        }
        if (qtdFalhas.size() !=0){
       List<LogStatusServicoNfe> logs = repository.findAll();
        for (LogStatusServicoNfe log: logs) {
            int falhas = 0;
            if (log.getAutorizacao4() != Status.verde.valorStatus) {
                falhas++;
            }
            if (log.getAutorizacao4() != Status.verde.valorStatus) {
                falhas++;
            }
            if (log.getRetornoAutorizacao4() != Status.verde.valorStatus) {
                falhas++;
            }
            if (log.getInutilizacao4() != Status.verde.valorStatus) {
                falhas++;
            }
            if (log.getConsultaProtocolo4() != Status.verde.valorStatus) {
                falhas++;
            }
            if (log.getStatusServico4() != Status.verde.valorStatus) {
                falhas++;
            }
            if (log.getTempoMedio() != Status.verde.valorStatus) {
                falhas++;
            }
            if (log.getConsultaCadastro4() != Status.verde.valorStatus) {
                falhas++;
            }
            if (log.getRecepcaoEvento4() != Status.verde.valorStatus) {
                falhas++;
            }
                qtdFalhas.set(log.getEstado().getId(),qtdFalhas.get(log.getEstado().getId()) + falhas);


        }
            int maiorNumeroFalhas = Collections.max(qtdFalhas);
            return repository.findByEstadoId(qtdFalhas.indexOf(maiorNumeroFalhas));
       }else {
            return new ArrayList<>();

        }
    }


    @GetMapping
    public List<LogStatusServicoNfe> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

}
