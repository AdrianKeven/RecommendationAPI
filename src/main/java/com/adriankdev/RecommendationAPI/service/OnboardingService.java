package com.adriankdev.RecommendationAPI.service;

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

    public void salvarAvaliacoes(Usuario usuario, List<Review> reviews) {

        for (Review review : reviews) {

            review.setUsuario(usuario);
            reviewRepository.save(review);

            if (review.getNota() >= 4) {
                Favorito favorito = new Favorito();
                favorito.setUsuario(usuario);
                favorito.setFilme(review.getFilme());
                favoritoRepository.save(favorito);

            }

        }
    }
}
