package com.adriankdev.RecommendationAPI.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private String email;

    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Review> reviews;

    @OneToMany(mappedBy = "usuario")
    private List<Favorito> favoritos;

}
