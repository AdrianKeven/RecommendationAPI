package com.adriankdev.RecommendationAPI.repository;

import java.util.List;

import com.adriankdev.RecommendationAPI.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavoritoRepository extends JpaRepository<Favorito,Long>{

    List<Favorito> findByUsuarioId(Long usuarioId);

    boolean existsByUsuarioIdAndFilmeId(Long usuarioId, Long filmeId);

    @Query("""
        SELECT fav.filme.id, COUNT(fav)
        FROM Favorito fav
        GROUP BY fav.filme.id
    """)
    List<Object[]> countFavoritosPorFilme();

    void deleteByUsuarioIdAndFilmeId(Long usuarioId, Long filmeId);
}
