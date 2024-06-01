package br.com.biscoithor.soundmatch.main;

import br.com.biscoithor.soundmatch.model.Artista;
import br.com.biscoithor.soundmatch.model.Musica;
import br.com.biscoithor.soundmatch.model.TipoArtista;
import br.com.biscoithor.soundmatch.repository.ArtistaRepository;
import br.com.biscoithor.soundmatch.service.ConsultaChatGPT;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {


    private final ArtistaRepository repositorio;
    private Scanner leitura = new Scanner(System.in);

    public Main(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void showMenu()
    {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Screen Sound Músicas ***                    
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    5- Pesquisar dados sobre um artista
                                    
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarDadosDoArtista();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Informe o Artista que deseja listar as músicas:");
        var artista = leitura.nextLine();
        List<Musica> musicasDoArtista = repositorio.buscaMusicasPorArtista(artista);
        musicasDoArtista.forEach(System.out::println);
    }

    private void pesquisarDadosDoArtista() {
        System.out.println("Sobre qual artista você quer saber?");
        var artista = leitura.nextLine();
        var resposta = ConsultaChatGPT.obterInformacao(artista);
        System.out.println(resposta.trim());
        
    }

    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a->a.getMusicas().forEach(System.out::println));
    }

    private void cadastrarMusicas() {
        System.out.println("Cadastrar música de que artista? ");
        var nome = leitura.nextLine();
        Optional<Artista> optArtista = repositorio.findArtista(nome);
        if(optArtista.isPresent())
        {
            System.out.println("Informe o Título da Música:");
            var tituloMusica = leitura.nextLine();

            Musica musica = new Musica(tituloMusica);
            musica.setArtista(optArtista.get());
            optArtista.get().getMusicas().add(musica);
            repositorio.save(optArtista.get());
        }
        else
        {
            System.out.println("Artista não encontrado!");
        }

    }

    private void cadastrarArtistas() {
        var cadastrarNovo = "s";
        while (cadastrarNovo.equalsIgnoreCase("s"))
        {
        System.out.println("Informe o nome deste Artista");
        var nome = leitura.nextLine();
        System.out.println("Informe o tipo deste artista: (Solo, Dupla ou Banda)");
        var tipo = leitura.nextLine();
        TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
        Artista artista = new Artista(nome, tipoArtista);
        repositorio.save(artista);
        System.out.println("Cadastrar novo Artista? (S/N)");
        cadastrarNovo = leitura.nextLine();
        }
    }
}
