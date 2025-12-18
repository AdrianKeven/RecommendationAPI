package com.adriankdev.RecommendationAPI.DTO;

import lombok.Data;

@Data
public class FilmeDTO {

    private Long id;
    private String titulo;
    private Double notaMedia;
    private Integer anoLancamento;
    private String genero;
}
