package br.com.claudemir.literalura.util;

import br.com.claudemir.literalura.Obra;
import br.com.claudemir.literalura.db_livros.AddTabela;

public class ObraInfoProcessor {

    public AddTabela processarObraInfo(Obra obraInfo) {
        AddTabela addTabela = new AddTabela();

        // Mapeia os dados de 'Obra' para 'AddTabela'
        addTabela.setTituloObra(obraInfo.getTitulo());
        addTabela.setAutor(obraInfo.getEscritor());
        addTabela.setIdioma(obraInfo.getIdioma());
        addTabela.setAnoLancamento(obraInfo.getAnoLancamento());
        addTabela.setAnoNascimento(obraInfo.getAnoNascimento());
        addTabela.setAnoFalecimento(obraInfo.getAnoFalecimento());

        // Garante um valor não-nulo para qtDownloads
        addTabela.setQtDownloads(obraInfo.getQtDownloads() != 0 ? obraInfo.getQtDownloads() : 0);

        addTabela.setLinkDownload(obraInfo.getLinkDownloads());

        return addTabela;
    }
}
