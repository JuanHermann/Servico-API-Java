package com.example.ServicoApiJava.Controllers;

import com.example.ServicoApiJava.Models.Estado;
import com.example.ServicoApiJava.Repositorys.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/estados")
public class EstadoController {

    private EstadoRepository repository;

    public EstadoController(EstadoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Estado> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }



}
