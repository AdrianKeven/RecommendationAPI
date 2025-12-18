package com.adriankdev.RecommendationAPI.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    @Getter
    private String email;

    private String senha;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Review> reviews;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Favorito> favoritos;

}
