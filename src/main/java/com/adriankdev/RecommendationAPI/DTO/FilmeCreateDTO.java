package com.adriankdev.RecommendationAPI.DTO;

import lombok.Data;

@Data
public class FilmeCreateDTO {

    private String titulo;
    private String descricao;
    private String diretor;
    private Integer anoLancamento;
    private String genero;
    private String imagemUrl;
}

