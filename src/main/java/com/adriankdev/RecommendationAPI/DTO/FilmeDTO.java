package com.adriankdev.RecommendationAPI.DTO;

import lombok.Data;

@Data
public class FilmeDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String diretor;
    private Integer anoLancamento;
    private String genero;
    private Double notaMedia;
    private String imagemUrl;
}
