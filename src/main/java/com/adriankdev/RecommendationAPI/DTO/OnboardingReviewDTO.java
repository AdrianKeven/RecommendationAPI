package com.adriankdev.RecommendationAPI.DTO;

import lombok.Data;

@Data
public class OnboardingReviewDTO {

    private Long filmeId;
    private Integer nota;
    private String comentario;
}