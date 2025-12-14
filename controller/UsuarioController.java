package com.adriankdev.RecommendationAPI.controller;

import com.adriankdev.RecommendationAPI.model.Usuario;
import com.adriankdev.RecommendationAPI.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @GetMapping("/buscar")
    public Usuario buscarPorEmail(@RequestParam String email){
        return service.buscarPorEmail(email);
    }
}
