package com.adriankdev.RecommendationAPI.controller;

import com.adriankdev.RecommendationAPI.model.Review;
import com.adriankdev.RecommendationAPI.model.Filme;
import com.adriankdev.RecommendationAPI.model.Usuario;
import com.adriankdev.RecommendationAPI.service.ReviewService;
import com.adriankdev.RecommendationAPI.service.FilmeService;
import com.adriankdev.RecommendationAPI.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UsuarioService usuarioService;
    private final FilmeService filmeService;

    public ReviewController(ReviewService reviewService,
                            UsuarioService usuarioService,
                            FilmeService filmeService) {
        this.reviewService = reviewService;
        this.usuarioService = usuarioService;
        this.filmeService = filmeService;
    }

    public Review criar(@RequestParam Long usuarioId,
                        @RequestParam Long filmeId,
                        @RequestBody Review review) {

        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        Filme filme = filmeService.buscarPorId(filmeId);

        review.setUsuario(usuario);
        review.setFilme(filme);

        return reviewService.salvar(review);
    }

}
