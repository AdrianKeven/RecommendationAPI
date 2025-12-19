package com.adriankdev.RecommendationAPI.controller;

import com.adriankdev.RecommendationAPI.DTO.FilmeDTO;
import com.adriankdev.RecommendationAPI.DTO.OnboardingReviewDTO;
import com.adriankdev.RecommendationAPI.Mapper.FilmeMapper;
import com.adriankdev.RecommendationAPI.model.Filme;
import com.adriankdev.RecommendationAPI.model.Usuario;
import com.adriankdev.RecommendationAPI.service.OnboardingService;
import com.adriankdev.RecommendationAPI.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onboarding")
public class OnboardingController {

    private final OnboardingService onboardingService;
    private final UsuarioService usuarioService;

    public OnboardingController(OnboardingService onboardingService,
                                UsuarioService usuarioService) {
        this.onboardingService = onboardingService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/filmes")
    public List<FilmeDTO> listarFilmes(@RequestParam(defaultValue = "10") int limite){
    List<Filme> filmeList= onboardingService.listarFilmesParaAvaliacao(limite);
        return filmeList.stream()
                .map(FilmeMapper::toDTO)
                .toList();
    }

    @PostMapping("/avaliacoes")
    public void salvarAcaliacoes(@RequestParam Long usuarioId,
                                 @RequestBody List<OnboardingReviewDTO> dto) {

        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        onboardingService.salvarAvaliacoes(usuario,dto);
    }
}
