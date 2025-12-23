package com.adriankdev.RecommendationAPI.service;

import com.adriankdev.RecommendationAPI.DTO.FilmeDTO;
import com.adriankdev.RecommendationAPI.Mapper.FilmeMapper;
import com.adriankdev.RecommendationAPI.model.Filme;
import com.adriankdev.RecommendationAPI.repository.FilmeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository repository;
    private static final String DIRETORIO_IMAGENS =
            "src/main/resource/static/assets/filmes/";

    public FilmeService(FilmeRepository repository) {
        this.repository = repository;
    }

    public Filme salvar(Filme filme) {
        return repository.save(filme);
    }

    public FilmeDTO criar(
            String titulo,
            String descricao,
            String diretor,
            Integer anoLancamento,
            String genero,
            MultipartFile imagem
    ) {

        Filme filme = new Filme();
        filme.setTitulo(titulo);
        filme.setDescricao(descricao);
        filme.setDiretor(diretor);
        filme.setAnoLancamento(anoLancamento);
        filme.setGenero(genero);

        // 🔹 Upload da imagem
        if (imagem != null && !imagem.isEmpty()) {
            try {
                String nomeArquivo = System.currentTimeMillis()
                        + "_" + imagem.getOriginalFilename();

                Path caminho = Paths.get(DIRETORIO_IMAGENS + nomeArquivo);
                Files.createDirectories(caminho.getParent());
                Files.write(caminho, imagem.getBytes());

                filme.setImagemUrl("/assets/filmes/" + nomeArquivo);

            } catch (IOException e) {
                throw new RuntimeException("Erro ao salvar imagem", e);
            }
        } else {
            // 🔹 SEM imagem cadastrada
            filme.setImagemUrl(null);
        }

        Filme salvo = repository.save(filme);
        return FilmeMapper.toDTO(salvo);
    }

    public List<FilmeDTO> listar() {
        return repository.findAll()
                .stream()
                .map(FilmeMapper::toDTO)
                .toList();
    }

    public Filme buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Filme nao encontrado"));
    }
}
