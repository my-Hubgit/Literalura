package br.com.claudemir.literalura.principal;

import br.com.claudemir.literalura.api.buscaApi;
import br.com.claudemir.literalura.Obra;
import br.com.claudemir.literalura.db_livros.AddTabela;
import br.com.claudemir.literalura.db_livros.AddTabelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "br.com.claudemir.literalura")
public class Principal implements CommandLineRunner {

    @Autowired
    private AddTabelaService addTabelaService;

    private Scanner leitura = new Scanner(System.in);

    @Override
    public void run(String... args) {
        exibeMenu();
    }

    public void exibeMenu() {
        int opcao = -1;

        while (opcao != 0) {
            String menu = """
                    *** LiterAlura ***

                    1- Pesquisar por obra.
                    2- Listar obras cadastradas
                    3- Listar autores cadastrados
                    4- Listar autores vivos em um determinado ano
                    5- Listar obras em um determinado idioma
                    

                    0 - Sair
                    """;

            System.out.println(menu);

            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    pesquisarObra();
                    break;
                case 2:
                    listarObrasCadastradas();
                    break;
                case 3:
                    listarAutoresCadastrados();
                    break;
                case 4:
                    listarAutoresVivosNoPeriodo();
                    break;
                case 5:
                    listarObrasPorIdioma();
                    break;

                case 0:
                    System.out.println("Encerrando a aplicação!");
                    System.exit(0); // Encerra a aplicação break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void pesquisarObra() {
        // Solicita a obra ao usuário
        System.out.println("Digite a obra a ser pesquisada:");
        String obra = leitura.nextLine();

        // Chama a API para buscar a obra
        buscaApi api = new buscaApi();
        String jsonResposta = api.pesquisarObra(obra);

        // Processa a resposta JSON e mapeia para a classe Obra
        JsonObject jsonObject = JsonParser.parseString(jsonResposta).getAsJsonObject();
        JsonArray results = jsonObject.getAsJsonArray("results");

        if (results.size() > 0) {
            JsonObject result = results.get(0).getAsJsonObject();
            Obra obraInfo = new Obra();
            obraInfo.setTitulo(result.get("title").getAsString());
            obraInfo.setAnoLancamento(result.has("publication_year") ? result.get("publication_year").getAsInt() : 0);
            obraInfo.setIdioma(result.getAsJsonArray("languages").get(0).getAsString());
            obraInfo.setEscritor(result.get("authors").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString());
            obraInfo.setAnoNascimento(result.get("authors").getAsJsonArray().get(0).getAsJsonObject().get("birth_year").getAsInt());
            obraInfo.setAnoFalecimento(result.get("authors").getAsJsonArray().get(0).getAsJsonObject().get("death_year").getAsInt());
            obraInfo.setQtDownloads(result.has("download_count") ? result.get("download_count").getAsInt() : 0);
            obraInfo.setLinkDownloads(result.get("formats").getAsJsonObject().has("application/octet-stream") ? result.get("formats").getAsJsonObject().get("application/octet-stream").getAsString() : "N/A");

            // Exibe as informações da obra
            System.out.println("Resultado da pesquisa:");
            System.out.println(obraInfo.toString());

            // Pergunta se o usuário deseja cadastrar a obra no banco de dados
            System.out.println("Deseja cadastrar a obra no banco de dados? (S/N)");
            String cadastrarObra = leitura.nextLine();

            if (cadastrarObra.equalsIgnoreCase("S")) {
                // Processa os dados da obra e mapeia para AddTabela
                AddTabela addTabela = new AddTabela();
                addTabela.setAnoLancamento(obraInfo.getAnoLancamento());
                addTabela.setAutor(obraInfo.getEscritor());
                addTabela.setAnoFalecimento(obraInfo.getAnoFalecimento());
                addTabela.setAnoNascimento(obraInfo.getAnoNascimento());
                addTabela.setIdioma(obraInfo.getIdioma());
                addTabela.setLinkDownload(obraInfo.getLinkDownloads());
                addTabela.setQtDownloads(obraInfo.getQtDownloads());
                addTabela.setTituloObra(obraInfo.getTitulo());

                // Salva os dados no banco de dados
                addTabelaService.salvarLivro(addTabela);
                System.out.println("A obra foi cadastrada no banco de dados.");
            } else {
                System.out.println("Apenas pesquisa realizada.");
            }
        } else {
            System.out.println("Nenhum resultado encontrado.");
        }
    }

    private void listarObrasCadastradas() {
        System.out.println("As obras cadastradas são:");
        List<AddTabela> obras = addTabelaService.listarObras();

        for (AddTabela obra : obras) {
            System.out.println("******************************************************************");
            System.out.println("* Titulo: " + obra.getTituloObra());
            System.out.println("* Autor: " + inverterNomeAutor(obra.getAutor()));
            System.out.println("* Idioma: " + obra.getIdioma());
            System.out.println("* Downloads: " + obra.getQtDownloads());
            System.out.println("* Link: " + obra.getLinkDownload());
            System.out.println("******************************************************************");
        }
    }

    private void listarAutoresCadastrados() {
        System.out.println("Os autores cadastrados são:");
        List<Object[]> autores = addTabelaService.listarAutores();

        for (Object[] autor : autores) {
            System.out.println("*****************************************************");
            System.out.println("* Autor: " + inverterNomeAutor((String) autor[0]));
            System.out.println("* Idioma: " + autor[1]);
            System.out.println("* Ano de Nascimento: " + autor[2]);
            System.out.println("* Ano de Falecimento: " + autor[3]);
            System.out.println("*****************************************************");
        }
    }

    private void listarAutoresVivosNoPeriodo() {
        System.out.println("Digite o ano inicial:");
        int anoInicial = leitura.nextInt();
        leitura.nextLine();

        System.out.println("Digite o ano final:");
        int anoFinal = leitura.nextInt();
        leitura.nextLine();

        List<Object[]> autores = addTabelaService.listarAutoresVivosNoPeriodo(anoInicial, anoFinal);

        for (Object[] autor : autores) {
            System.out.println("*****************************************************");
            System.out.println("* Autor: " + inverterNomeAutor((String) autor[0]));
            System.out.println("* Ano de Nascimento: " + autor[1]);
            System.out.println("* Ano de Falecimento: " + autor[2]);
            System.out.println("*****************************************************");
        }
    }

    private void listarObrasPorIdioma() {
        List<String> idiomas = addTabelaService.listarIdiomasUnicos();

        System.out.println("****************** Idiomas das Obras encontradas no Banco de dados ******************");
        for (String idioma : idiomas) {
            System.out.println(idioma);
        }
        System.out.println("********************************************************************");

        System.out.println("Digite o idioma desejado:");
        String idioma = leitura.nextLine();

        List<Object[]> obras = addTabelaService.listarObrasPorIdioma(idioma);

        for (Object[] obra : obras) {
            System.out.println("*****************************************************");
            System.out.println("* Obra: " + obra[0]);
            System.out.println("* Autor: " + inverterNomeAutor((String) obra[1]));
            System.out.println("* Link de download: " + obra[2]);
            System.out.println("*****************************************************");
        }
    }

    private String inverterNomeAutor(String autor) {
        String[] nomePartes = autor.split(", ");
        if (nomePartes.length == 2) {
            return nomePartes[1] + " " + nomePartes[0];
        }
        return autor; // Retorna como está se não conseguir inverter
    }

    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }
}
