package com.adriankdev.RecommendationAPI.DTO;

import lombok.Data;

@Data
public class ReviewDTO {

    private Long filmeId;
    private Double nota;
    private String comentario;
}
