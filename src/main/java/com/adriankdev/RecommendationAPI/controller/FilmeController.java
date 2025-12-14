package com.adriankdev.RecommendationAPI.controller;

import com.adriankdev.RecommendationAPI.model.Filme;
import com.adriankdev.RecommendationAPI.service.FilmeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @PostMapping
    public Filme criar(@RequestBody Filme filme) {
        return service.salvar(filme);
    }

    @GetMapping
    public List<Filme> listar() {
        return service.listarTodos();
    }
}
