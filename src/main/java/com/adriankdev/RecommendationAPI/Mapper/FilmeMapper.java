package com.adriankdev.RecommendationAPI.Mapper;


import com.adriankdev.RecommendationAPI.DTO.FilmeDTO;
import com.adriankdev.RecommendationAPI.model.Filme;

public class FilmeMapper {

    public static FilmeDTO toDTO(Filme filme){
        FilmeDTO dto = new FilmeDTO();
        dto.setId(filme.getId());
        dto.setTitulo(filme.getTitulo());
        dto.setNotaMedia(filme.getNotaMedia());
        dto.setAnoLancamento(filme.getAnoLancamento());
        dto.setGenero(filme.getGenero());
        return dto;
    }
}
