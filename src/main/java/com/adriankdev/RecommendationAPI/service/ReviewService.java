package com.adriankdev.RecommendationAPI.service;

import com.adriankdev.RecommendationAPI.model.Favorito;
import com.adriankdev.RecommendationAPI.model.Review;
import com.adriankdev.RecommendationAPI.repository.FavoritoRepository;
import com.adriankdev.RecommendationAPI.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository repository;
    private final FavoritoRepository favoritoRepository;

    public ReviewService(ReviewRepository repository, FavoritoRepository favoritoRepository) {
        this.repository = repository;
        this.favoritoRepository = favoritoRepository;
    }
    @Transactional
    public Review salvar(Review review) {

        Review savedReview = repository.save(review);

        if (review.getNota() >= 4) {

            boolean jaExiste = favoritoRepository
                    .existsByUsuarioIdAndFilmeId(
                            review.getUsuario().getId(),
                            review.getFilme().getId()
                    );

            if (!jaExiste) {
                Favorito fav = new Favorito();
                fav.setUsuario(review.getUsuario());
                fav.setFilme(review.getFilme());
                favoritoRepository.save(fav);
            }
        }

        if (review.getNota() < 4) {
            favoritoRepository.deleteByUsuarioIdAndFilmeId(
                    review.getUsuario().getId(),
                    review.getFilme().getId()
            );
        }


        return savedReview;
    }
}
