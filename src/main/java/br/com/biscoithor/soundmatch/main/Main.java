package br.com.biscoithor.soundmatch.main;

import br.com.biscoithor.soundmatch.model.Artista;
import br.com.biscoithor.soundmatch.model.TipoArtista;
import br.com.biscoithor.soundmatch.repository.ArtistaRepository;
import org.springframework.cglib.core.ClassInfo;

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
    }

    private void pesquisarDadosDoArtista() {
        
    }

    private void listarMusicas() {
        
    }

    private void cadastrarMusicas() {
        
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
