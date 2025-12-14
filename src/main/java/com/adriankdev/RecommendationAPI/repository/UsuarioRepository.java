package com.adriankdev.RecommendationAPI.repository;

import com.adriankdev.RecommendationAPI.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByEmail(String email);
}
