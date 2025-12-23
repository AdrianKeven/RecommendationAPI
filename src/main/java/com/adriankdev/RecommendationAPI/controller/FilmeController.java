package com.adriankdev.RecommendationAPI.controller;

import com.adriankdev.RecommendationAPI.DTO.FilmeCreateDTO;
import com.adriankdev.RecommendationAPI.DTO.FilmeDTO;
import com.adriankdev.RecommendationAPI.Mapper.FilmeMapper;
import com.adriankdev.RecommendationAPI.model.Filme;
import com.adriankdev.RecommendationAPI.service.FilmeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.adriankdev.RecommendationAPI.Mapper.FilmeMapper.toEntity;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FilmeDTO criar(
            @RequestParam String titulo,
            @RequestParam(required = false) String descricao,
            @RequestParam String diretor,
            @RequestParam Integer anoLancamento,
            @RequestParam String genero,
            @RequestParam(required = false) MultipartFile imagem
    ) {
        return service.criar(titulo, descricao, diretor, anoLancamento, genero, imagem);
    }

    @GetMapping
    public List<FilmeDTO> listar() {
        return service.listar();
    }

}

