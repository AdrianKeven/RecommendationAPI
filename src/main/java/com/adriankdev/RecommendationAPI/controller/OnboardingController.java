package com.adriankdev.RecommendationAPI.controller;

import com.adriankdev.RecommendationAPI.model.Filme;
import com.adriankdev.RecommendationAPI.model.Review;
import com.adriankdev.RecommendationAPI.model.Usuario;
import com.adriankdev.RecommendationAPI.service.OnboardingService;
import com.adriankdev.RecommendationAPI.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onborading")
public class OnboardingController {

    private final OnboardingService onboardingService;
    private final UsuarioService usuarioService;

    public OnboardingController(OnboardingService onboardingService,
                                UsuarioService usuarioService) {
        this.onboardingService = onboardingService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/filmes")
    public List<Filme> listarFilmes() {
        return onboardingService.listarFilmesParaAvaliacao(10);
    }

    @PostMapping("/avaliacoes")
    public void salvarAcaliacoes(@RequestParam Long usuarioId,
                                 @RequestBody List<Review> reviews) {

        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        onboardingService.salvarAvaliacoes(usuario,reviews);
    }
}
