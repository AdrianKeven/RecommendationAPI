package com.adriankdev.RecommendationAPI.repository;

import com.adriankdev.RecommendationAPI.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUsuarioId(Long usuarioId);

    List<Review> findByFilmeId(Long filmeId);

    List<Review> findByUsuarioIdAndOnboardingTrue(Long usuarioId);
}

