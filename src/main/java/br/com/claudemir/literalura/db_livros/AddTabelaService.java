package br.com.claudemir.literalura.db_livros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddTabelaService {

    private final AddTabelaRepository addTabelaRepository;

    @Autowired
    public AddTabelaService(AddTabelaRepository addTabelaRepository) {
        this.addTabelaRepository = addTabelaRepository;
    }

    public void salvarLivro(AddTabela livro) {
        try {
            // Salvando o livro no banco de dados
            addTabelaRepository.save(livro);
            System.out.println("Livro salvo com sucesso no banco de dados:");
            System.out.println(livro);
        } catch (Exception e) {
            System.out.println("Erro ao salvar o livro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<AddTabela> listarObras() {
        return addTabelaRepository.findAll();
    }

    public List<Object[]> listarAutores() {
        return addTabelaRepository.findAllAutores();
    }

    public List<Object[]> listarAutoresVivosNoPeriodo(int anoInicial, int anoFinal) {
        return addTabelaRepository.findAutoresVivosNoPeriodo(anoInicial, anoFinal);
    }

    public List<String> listarIdiomasUnicos() {
        return addTabelaRepository.findIdiomasUnicos();
    }

    public List<Object[]> listarObrasPorIdioma(String idioma) {
        return addTabelaRepository.findObrasPorIdioma(idioma);
    }
}
