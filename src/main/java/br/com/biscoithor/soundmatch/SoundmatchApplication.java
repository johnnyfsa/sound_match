package br.com.biscoithor.soundmatch;

import br.com.biscoithor.soundmatch.main.Main;
import br.com.biscoithor.soundmatch.model.Artista;
import br.com.biscoithor.soundmatch.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoundmatchApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(SoundmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main menuPrincipal = new Main(repositorio);
		menuPrincipal.showMenu();
	}
}
