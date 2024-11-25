package br.com.claudemir.literalura.db_livros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface AddTabelaRepository extends JpaRepository<AddTabela, Long> {

    @Query("SELECT DISTINCT a.autor, a.idioma, a.anoNascimento, a.anoFalecimento FROM AddTabela a")
    List<Object[]> findAllAutores();

    @Query("SELECT DISTINCT a.autor, a.anoNascimento, a.anoFalecimento FROM AddTabela a WHERE a.anoNascimento <= :anoFinal AND (a.anoFalecimento >= :anoInicial OR a.anoFalecimento IS NULL)")
    List<Object[]> findAutoresVivosNoPeriodo(int anoInicial, int anoFinal);

    @Query("SELECT DISTINCT a.idioma FROM AddTabela a")
    List<String> findIdiomasUnicos();

    @Query("SELECT a.tituloObra, a.autor, a.linkDownload FROM AddTabela a WHERE LOWER(a.idioma) = LOWER(:idioma)")
    List<Object[]> findObrasPorIdioma(String idioma);
}
