package com.adriankdev.RecommendationAPI.DTO;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private Integer nota;
    private String comentario;
    private String nomeUsuario;
    private String tituloFilme;
    private boolean onboarding;
}
