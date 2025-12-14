package com.adriankdev.RecommendationAPI.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String diretor;
    private Integer anoLancamento;
    private String genero;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public double getNotaMedia() {
        if (reviews == null || reviews.isEmpty()) return 0.0;
        return reviews.stream()
                .mapToDouble(Review::getNota)
                .average()
                .orElse(0.0);
    }

}
