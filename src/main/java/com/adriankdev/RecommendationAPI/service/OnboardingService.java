package com.adriankdev.RecommendationAPI.service;

import com.adriankdev.RecommendationAPI.DTO.OnboardingReviewDTO;
import com.adriankdev.RecommendationAPI.model.Favorito;
import com.adriankdev.RecommendationAPI.model.Filme;
import com.adriankdev.RecommendationAPI.model.Review;
import com.adriankdev.RecommendationAPI.model.Usuario;
import com.adriankdev.RecommendationAPI.repository.FavoritoRepository;
import com.adriankdev.RecommendationAPI.repository.FilmeRepository;
import com.adriankdev.RecommendationAPI.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
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
        return filmes.stream().limit(quantidade).toList();
    }

    @Transactional
    public void salvarAvaliacoes(Usuario usuario, List<OnboardingReviewDTO> dtos) {

        for (OnboardingReviewDTO dto : dtos) {

            Filme filme = filmeRepository.findById(dto.getFilmeId())
                    .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

            Review review = new Review();
            review.setNota(dto.getNota());
            review.setComentario(dto.getComentario());
            review.setUsuario(usuario);
            review.setFilme(filme);
            review.setOnboarding(true); // IMPORTANTE

            reviewRepository.save(review);

            if (dto.getNota() >= 4) {
                Favorito fav = new Favorito();
                fav.setUsuario(usuario);
                fav.setFilme(filme);
                favoritoRepository.save(fav);
            }
        }
    }

    /**
     * Perfil de gosto baseado APENAS no onboarding
     */
    public Map<String, Integer> getPerfilGenero(Long usuarioId) {

        List<Review> reviews =
                reviewRepository.findByUsuarioIdAndOnboardingTrue(usuarioId);

        Map<String, Integer> perfilGenero = new HashMap<>();

        for (Review review : reviews) {

            String genero = review.getFilme().getGenero();
            int nota = review.getNota();

            int peso = (nota <= 2) ? -2 : nota;
            perfilGenero.merge(genero, peso, Integer::sum);
        }

        return perfilGenero;
    }
}

