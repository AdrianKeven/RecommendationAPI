package com.adriankdev.RecommendationAPI.service;

import com.adriankdev.RecommendationAPI.model.Review;
import com.adriankdev.RecommendationAPI.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review salvar(Review review) {
        return repository.save(review);
    }
}
