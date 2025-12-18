package com.adriankdev.RecommendationAPI.repository;

import java.util.List;

import com.adriankdev.RecommendationAPI.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito,Long>{

    List<Favorito> findByUsuarioId(Long usuarioId);

    boolean existsByUsuarioIdAndFilmeId(Long usuarioId, Long filmeId);
}
