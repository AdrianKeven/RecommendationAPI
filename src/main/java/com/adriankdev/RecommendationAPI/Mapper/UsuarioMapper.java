package com.adriankdev.RecommendationAPI.Mapper;

import com.adriankdev.RecommendationAPI.DTO.UsuarioDTO;
import com.adriankdev.RecommendationAPI.model.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}
