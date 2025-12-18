package com.adriankdev.RecommendationAPI.controller;

import com.adriankdev.RecommendationAPI.model.Filme;
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
    public List<Filme> recomendar(@PathVariable Long usuarioId) {
        return service.recomendarPorUsuario(usuarioId);
    }
}
