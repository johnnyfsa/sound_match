package br.com.biscoithor.soundmatch.repository;

import br.com.biscoithor.soundmatch.model.Artista;
import br.com.biscoithor.soundmatch.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    @Query("SELECT a FROM Artista a WHERE a.nome = :nome")
    Optional<Artista> findArtista(String nome);

    @Query("SELECT a.musicas FROM Artista a WHERE a.nome ILIKE %:artista%")
    List<Musica> buscaMusicasPorArtista(String artista);
}
