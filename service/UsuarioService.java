package com.adriankdev.RecommendationAPI.service;

import com.adriankdev.RecommendationAPI.model.Usuario;
import com.adriankdev.RecommendationAPI.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        Usuario existente = repository.findByEmail(usuario.getEmail());

        if (existente != null) {
            throw new RuntimeException("Email já cadastrado");
        }

        return repository.save(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
