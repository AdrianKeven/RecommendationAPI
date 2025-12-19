package com.adriankdev.RecommendationAPI.DTO;

import lombok.Data;

@Data
public class UsuarioCreateDTO {
    private String nome;
    private String email;
    private String senha;
}
