package com.adriankdev.RecommendationAPI.DTO;

import lombok.Data;

@Data
public class ReviewCreateDTO {
    private Integer nota;
    private String comentario;
}
