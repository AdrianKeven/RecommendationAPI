package com.adriankdev.RecommendationAPI.controller;

import com.adriankdev.RecommendationAPI.DTO.FilmeDTO;
import com.adriankdev.RecommendationAPI.Mapper.FilmeMapper;
import com.adriankdev.RecommendationAPI.service.RecomendacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recomendacoes")
public class RecomendacaoController {

    private final RecomendacaoService service;

    public RecomendacaoController(RecomendacaoService service) {
        this.service = service;
    }

    @GetMapping("/{usuarioId}")
    public List<FilmeDTO> recomendar(@PathVariable Long usuarioId) {
        return service.recomendarPorUsuario(usuarioId)
                .stream()
                .map(FilmeMapper::toDTO)
                .toList();
    }
}
