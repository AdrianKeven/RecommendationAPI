package com.adriankdev.RecommendationAPI.Mapper;


import com.adriankdev.RecommendationAPI.DTO.ReviewDTO;
import com.adriankdev.RecommendationAPI.model.Review;

public class ReviewMapper {

    public static ReviewDTO toDTO(Review review){
        ReviewDTO dto = new ReviewDTO();
        dto.setFilmeId(review.getFilme().getId());
        dto.setNota(review.getNota());
        dto.setComentario(review.getComentario());
        return dto;
    }
}
