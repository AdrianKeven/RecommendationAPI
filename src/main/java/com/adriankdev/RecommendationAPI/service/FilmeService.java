package com.adriankdev.RecommendationAPI.service;

import com.adriankdev.RecommendationAPI.model.Filme;
import com.adriankdev.RecommendationAPI.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository repository;

    public FilmeService(FilmeRepository repository) {
        this.repository = repository;
    }

    public Filme salvar(Filme filme) {
        return repository.save(filme);
    }

    public List<Filme> listarTodos() {
        return repository.findAll();
    }

    public Filme buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
