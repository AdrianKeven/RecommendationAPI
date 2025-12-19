package com.adriankdev.RecommendationAPI.controller;

import com.adriankdev.RecommendationAPI.DTO.FilmeCreateDTO;
import com.adriankdev.RecommendationAPI.DTO.FilmeDTO;
import com.adriankdev.RecommendationAPI.Mapper.FilmeMapper;
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
    public FilmeDTO criar(@RequestBody FilmeCreateDTO dto) {
        Filme filme = FilmeMapper.toEntity(dto);
        Filme salvo = service.salvar(filme);
        return FilmeMapper.toDTO(salvo);
    }

    @GetMapping
    public List<FilmeDTO> listar() {
        return service.listarTodos()
                .stream()
                .map(FilmeMapper::toDTO)
                .toList();
    }
}

