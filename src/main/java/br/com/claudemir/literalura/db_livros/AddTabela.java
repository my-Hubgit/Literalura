package br.com.claudemir.literalura.db_livros;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class AddTabela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ano_lancamento")
    private int anoLancamento;

    @Column(name = "autor")
    private String autor;

    @Column(name = "ano_falecimento")
    private int anoFalecimento;

    @Column(name = "ano_nascimento")
    private int anoNascimento;

    @Column(name = "idioma")
    private String idioma;

    @Column(name = "link_download")
    private String linkDownload;

    @Column(name = "qt_downloads")
    private int qtDownloads;

    @Column(name = "titulo_obra")
    private String tituloObra;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(int anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getLinkDownload() {
        return linkDownload;
    }

    public void setLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
    }

    public int getQtDownloads() {
        return qtDownloads;
    }

    public void setQtDownloads(int qtDownloads) {
        this.qtDownloads = qtDownloads;
    }

    public String getTituloObra() {
        return tituloObra;
    }

    public void setTituloObra(String tituloObra) {
        this.tituloObra = tituloObra;
    }

    @Override
    public String toString() {
        return "AddTabela{" +
                "anoLancamento=" + anoLancamento +
                ", autor='" + autor + '\'' +
                ", anoFalecimento=" + anoFalecimento +
                ", anoNascimento=" + anoNascimento +
                ", idioma='" + idioma + '\'' +
                ", linkDownload='" + linkDownload + '\'' +
                ", qtDownloads=" + qtDownloads +
                ", tituloObra='" + tituloObra + '\'' +
                '}';
    }
}
