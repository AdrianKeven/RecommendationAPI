package com.adriankdev.RecommendationAPI.repository;

import com.adriankdev.RecommendationAPI.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme,Long> {

    List<Filme> findByTituloContainingIgnoreCase(String titulo);
    List<Filme> findByGeneroIgnoreCase(String genero);
}
