package com.adriankdev.RecommendationAPI.service;

import com.adriankdev.RecommendationAPI.DTO.OnboardingReviewDTO;
import com.adriankdev.RecommendationAPI.model.Favorito;
import com.adriankdev.RecommendationAPI.model.Filme;
import com.adriankdev.RecommendationAPI.model.Review;
import com.adriankdev.RecommendationAPI.model.Usuario;
import com.adriankdev.RecommendationAPI.repository.FavoritoRepository;
import com.adriankdev.RecommendationAPI.repository.FilmeRepository;
import com.adriankdev.RecommendationAPI.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OnboardingService {

    private final FilmeRepository filmeRepository;
    private final ReviewRepository reviewRepository;
    private final FavoritoRepository favoritoRepository;

    public OnboardingService(FilmeRepository filmeRepository,
                             ReviewRepository reviewRepository,
                             FavoritoRepository favoritoRepository) {
        this.filmeRepository = filmeRepository;
        this.reviewRepository = reviewRepository;
        this.favoritoRepository = favoritoRepository;
    }

    public List<Filme> listarFilmesParaAvaliacao(int quantidade) {
        List<Filme> filmes = filmeRepository.findAll();
        Collections.shuffle(filmes);

        return filmes.stream()
                .limit(quantidade)
                .toList();
    }

    public void salvarAvaliacoes(Usuario usuario, List<OnboardingReviewDTO> dtos) {

        for (OnboardingReviewDTO dto : dtos) {

            Filme filme = filmeRepository.findById(dto.getFilmeId())
                    .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

            Review review = new Review();
            review.setNota(dto.getNota());
            review.setComentario(dto.getComentario());
            review.setUsuario(usuario);
            review.setFilme(filme);

            reviewRepository.save(review);

            if (dto.getNota() >= 4) {
                Favorito fav = new Favorito();
                fav.setUsuario(usuario);
                fav.setFilme(filme);
                favoritoRepository.save(fav);
            }
        }
    }
}

