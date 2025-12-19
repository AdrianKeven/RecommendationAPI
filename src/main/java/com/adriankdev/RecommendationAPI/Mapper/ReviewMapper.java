package com.adriankdev.RecommendationAPI.Mapper;


import com.adriankdev.RecommendationAPI.DTO.ReviewCreateDTO;
import com.adriankdev.RecommendationAPI.DTO.ReviewDTO;
import com.adriankdev.RecommendationAPI.model.Filme;
import com.adriankdev.RecommendationAPI.model.Review;
import com.adriankdev.RecommendationAPI.model.Usuario;

public class ReviewMapper {

    public static ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getFilme().getId());
        dto.setNota(review.getNota());
        dto.setComentario(review.getComentario());
        dto.setOnboarding(review.isOnboarding());
        return dto;
    }

    public static Review toEntity(ReviewCreateDTO dto, Usuario usuario, Filme filme) {
        Review review = new Review();
        review.setNota(dto.getNota());
        review.setComentario(dto.getComentario());
        review.setUsuario(usuario);
        review.setFilme(filme);
        review.setOnboarding(dto.isOnboarding());
        return review;
    }

}

