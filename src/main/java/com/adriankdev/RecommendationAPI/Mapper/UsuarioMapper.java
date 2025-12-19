package com.adriankdev.RecommendationAPI.Mapper;

import com.adriankdev.RecommendationAPI.DTO.UsuarioCreateDTO;
import com.adriankdev.RecommendationAPI.DTO.UsuarioDTO;
import com.adriankdev.RecommendationAPI.model.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

    public static UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}
