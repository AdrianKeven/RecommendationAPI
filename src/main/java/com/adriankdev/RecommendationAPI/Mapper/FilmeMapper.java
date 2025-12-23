package com.adriankdev.RecommendationAPI.Mapper;

import com.adriankdev.RecommendationAPI.DTO.FilmeCreateDTO;
import com.adriankdev.RecommendationAPI.DTO.FilmeDTO;
import com.adriankdev.RecommendationAPI.model.Filme;

public class FilmeMapper {

    // CREATE
    public static Filme toEntity(FilmeCreateDTO dto) {
        Filme filme = new Filme();
        filme.setTitulo(dto.getTitulo());
        filme.setDescricao(dto.getDescricao());
        filme.setDiretor(dto.getDiretor());
        filme.setAnoLancamento(dto.getAnoLancamento());
        filme.setGenero(dto.getGenero());
        filme.setImagemUrl(dto.getImagemUrl());
        return filme;
    }

    // READ
    public static FilmeDTO toDTO(Filme filme) {
        FilmeDTO dto = new FilmeDTO();
        dto.setId(filme.getId());
        dto.setTitulo(filme.getTitulo());
        dto.setDescricao(filme.getDescricao());
        dto.setDiretor(filme.getDiretor());
        dto.setAnoLancamento(filme.getAnoLancamento());
        dto.setGenero(filme.getGenero());
        dto.setNotaMedia(filme.getNotaMedia());
        dto.setImagemUrl(filme.getImagemUrl());
        return dto;
    }
}
