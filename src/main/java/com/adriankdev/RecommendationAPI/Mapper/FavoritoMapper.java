package com.adriankdev.RecommendationAPI.Mapper;

import com.adriankdev.RecommendationAPI.DTO.FavoritoDTO;
import com.adriankdev.RecommendationAPI.model.Favorito;

public class FavoritoMapper {

    public static FavoritoDTO toDTO(Favorito favorito){
        FavoritoDTO dto = new FavoritoDTO();
        dto.setId(favorito.getId());
        return dto;
    }
}
