package br.com.claudemir.literalura;

public class Obra {
    private String titulo;
    private int anoLancamento;
    private String idioma;
    private String escritor;
    private int anoNascimento;
    private int anoFalecimento;
    private int qtDownloads;
    private String linkDownloads;

    @Override
    public String toString() {
        // Usando String.format para melhorar a legibilidade
        return String.format("Obra: %s%n" +
                        "Lançado em: %d%n" +
                        "Idioma: %s%n" +
                        "Escritor: %s%n" +
                        "Nascimento: %d%n" +
                        "Falecido em: %s%n" +
                        "Downloads: %d%n" +
                        "Link para Download: %s%n",
                titulo,
                anoLancamento,
                idioma,
                formatarNomeEscritor(escritor),
                anoNascimento,
                anoFalecimento > 0 ? anoFalecimento : "Ainda Vivo",  // Verifica se o ano de falecimento é válido
                qtDownloads,
                linkDownloads);
    }

    // Método para formatar o nome do escritor
    private String formatarNomeEscritor(String nome) {
        if (nome == null || nome.isEmpty()) {
            return "Desconhecido";  // Retorna "Desconhecido" caso o nome não esteja presente
        }

        // Se o nome está no formato "Sobrenome, Nome"
        if (nome.contains(", ")) {
            String[] partes = nome.split(", ");
            return partes[1] + " " + partes[0];
        }
        return nome;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public int getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(int anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public int getQtDownloads() {
        return qtDownloads;
    }

    public void setQtDownloads(int qtDownloads) {
        this.qtDownloads = qtDownloads;
    }

    public String getLinkDownloads() {
        return linkDownloads;
    }

    public void setLinkDownloads(String linkDownloads) {
        this.linkDownloads = linkDownloads;
    }
}
