package com.adriankdev.RecommendationAPI.service;

import com.adriankdev.RecommendationAPI.model.Favorito;
import com.adriankdev.RecommendationAPI.model.Filme;
import com.adriankdev.RecommendationAPI.model.Review;
import com.adriankdev.RecommendationAPI.repository.FavoritoRepository;
import com.adriankdev.RecommendationAPI.repository.FilmeRepository;
import com.adriankdev.RecommendationAPI.repository.ReviewRepository;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;
@Service
public class RecomendacaoService {

    private final FilmeRepository filmeRepository;
    private final FavoritoRepository favoritoRepository;
    private final ReviewRepository reviewRepository;
    private final OnboardingService onboardingService;

    public RecomendacaoService(
            FilmeRepository filmeRepository,
            FavoritoRepository favoritoRepository,
            ReviewRepository reviewRepository,
            OnboardingService onboardingService) {

        this.filmeRepository = filmeRepository;
        this.favoritoRepository = favoritoRepository;
        this.reviewRepository = reviewRepository;
        this.onboardingService = onboardingService;
    }

    public List<Filme> recomendarPorUsuario(Long usuarioId) {

        List<Review> reviews = reviewRepository.findByUsuarioId(usuarioId);
        List<Favorito> favoritos = favoritoRepository.findByUsuarioId(usuarioId);

        if (reviews.isEmpty() && favoritos.isEmpty()) {
            return recomendarPopulares();
        }

        if (favoritos.isEmpty()) {
            return recomendarPorPerfil(usuarioId);
        }

        return recomendarHibrido(usuarioId, favoritos, reviews);
    }

    // ===============================
    // 1️⃣ Populares
    // ===============================
    private List<Filme> recomendarPopulares() {
        return filmeRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Filme::getNotaMedia).reversed())
                .limit(20)
                .toList();
    }

    // ===============================
    // 2️⃣ Onboarding
    // ===============================
    private List<Filme> recomendarPorPerfil(Long usuarioId) {

        Map<String, Integer> perfilGenero =
                onboardingService.getPerfilGenero(usuarioId);

        Set<Long> vistos = reviewRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(r -> r.getFilme().getId())
                .collect(Collectors.toSet());

        Random random = new Random();

        return filmeRepository.findAll().stream()
                .filter(f -> !vistos.contains(f.getId()))
                .map(f -> {

                    double score = 0;
                    score += perfilGenero.getOrDefault(f.getGenero(), 0) * 2;
                    score += f.getNotaMedia() * 1.5;

                    if (random.nextDouble() < 0.15) {
                        score += 2;
                    }

                    f.setScore(score);
                    return f;
                })
                .sorted(Comparator.comparingDouble(Filme::getScore).reversed())
                .limit(20)
                .toList();
    }

    // ===============================
    // 3️⃣ Híbrido
    // ===============================
    private List<Filme> recomendarHibrido(
            Long usuarioId,
            List<Favorito> favoritos,
            List<Review> reviews) {

        Map<String, Long> pesoGenero = favoritos.stream()
                .collect(Collectors.groupingBy(
                        f -> f.getFilme().getGenero(),
                        Collectors.counting()
                ));

        Set<Long> vistos = reviews.stream()
                .map(r -> r.getFilme().getId())
                .collect(Collectors.toSet());

        Map<Long, Long> favoritosPorFilme =
                favoritoRepository.countFavoritosPorFilme()
                        .stream()
                        .collect(Collectors.toMap(
                                r -> (Long) r[0],
                                r -> (Long) r[1]
                        ));

        Random random = new Random();

        return filmeRepository.findAll().stream()
                .filter(f -> !vistos.contains(f.getId()))
                .map(f -> {

                    double score = 0;
                    score += pesoGenero.getOrDefault(f.getGenero(), 0L) * 3;
                    score += f.getNotaMedia() * 2;

                    long pop = favoritosPorFilme.getOrDefault(f.getId(), 0L);
                    score += pop * 0.5;

                    if (random.nextDouble() < 0.2) {
                        score += 1.5;
                    }

                    f.setScore(score);
                    return f;
                })
                .sorted(Comparator.comparingDouble(Filme::getScore).reversed())
                .limit(20)
                .toList();
    }
}
