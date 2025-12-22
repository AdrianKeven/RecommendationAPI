package com.adriankdev.RecommendationAPI.controller;

import com.adriankdev.RecommendationAPI.DTO.LoginDTO;
import com.adriankdev.RecommendationAPI.DTO.UsuarioCreateDTO;
import com.adriankdev.RecommendationAPI.DTO.UsuarioDTO;
import com.adriankdev.RecommendationAPI.Mapper.UsuarioMapper;
import com.adriankdev.RecommendationAPI.model.Usuario;
import com.adriankdev.RecommendationAPI.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public UsuarioDTO criar(@RequestBody UsuarioCreateDTO dto) {

        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario salvo = service.salvar(usuario);

        return UsuarioMapper.toDTO(salvo);
    }

    @GetMapping("/{id}")
    public UsuarioDTO buscarPorId(@PathVariable Long id){

        Usuario usuario = service.buscarPorId(id);
        return UsuarioMapper.toDTO(usuario);
    }

    @GetMapping("/buscar")
    public UsuarioDTO buscarPorEmail(@RequestParam String email){

        Usuario usuario = service.buscarPorEmail(email);
        return UsuarioMapper.toDTO(usuario);
    }

    @PostMapping("/login")
    public UsuarioDTO login(@RequestBody LoginDTO dto) {

        Usuario usuario = service.buscarPorEmail(dto.getEmail());

        if (!usuario.getSenha().equals(dto.getSenha())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Senha inválida"
            );
        }

        return UsuarioMapper.toDTO(usuario);
    }

}
