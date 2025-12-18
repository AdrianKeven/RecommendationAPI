package com.adriankdev.RecommendationAPI.service;

import com.adriankdev.RecommendationAPI.model.Favorito;
import com.adriankdev.RecommendationAPI.model.Filme;
import com.adriankdev.RecommendationAPI.repository.FavoritoRepository;
import com.adriankdev.RecommendationAPI.repository.FilmeRepository;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecomendacaoService {

    private final FavoritoRepository favoritoRepository;
    private final FilmeRepository filmeRepository;

    public RecomendacaoService(FavoritoRepository favoritoRepository,
                               FilmeRepository filmeRepository) {

        this.favoritoRepository = favoritoRepository;
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> recomendarPorUsuario(Long usuarioId) {

        List<Favorito> favoritos = favoritoRepository.findByUsuarioId(usuarioId);

        if (favoritos.isEmpty()) {
            return filmeRepository.findAll();
        }

        // Gêneros preferidos
        Set<String> generosFavoritos = favoritos.stream()
                .map(f -> f.getFilme().getGenero())
                .collect(Collectors.toSet());

        // Filmes recomendados
        return filmeRepository.findAll().stream()
                .filter(f -> generosFavoritos.contains(f.getGenero()))
                .sorted(Comparator.comparingDouble(Filme::getNotaMedia).reversed())
                .collect(Collectors.toList());
    }
}

