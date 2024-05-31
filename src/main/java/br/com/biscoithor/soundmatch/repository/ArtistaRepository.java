package br.com.biscoithor.soundmatch.repository;

import br.com.biscoithor.soundmatch.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
