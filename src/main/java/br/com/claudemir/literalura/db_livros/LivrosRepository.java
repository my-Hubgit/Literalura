package br.com.claudemir.literalura.db_livros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivrosRepository extends JpaRepository<AddTabela, Long> {
}
