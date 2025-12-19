package com.adriankdev.RecommendationAPI.controller;

import com.adriankdev.RecommendationAPI.DTO.ReviewCreateDTO;
import com.adriankdev.RecommendationAPI.DTO.ReviewDTO;
import com.adriankdev.RecommendationAPI.Mapper.ReviewMapper;
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

    @PostMapping
    public ReviewDTO criar(@RequestParam Long usuarioId,
                           @RequestParam Long filmeId,
                           @RequestBody ReviewCreateDTO dto) {

        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        Filme filme = filmeService.buscarPorId(filmeId);

        Review review = ReviewMapper.toEntity(dto, usuario, filme);
        Review salvo = reviewService.salvar(review);

        return ReviewMapper.toDTO(salvo);
    }

}
