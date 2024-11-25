package br.com.claudemir.literalura.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class buscaApi {
    public String pesquisarObra(String termoBusca) {
        // Monta a URL com o termo de busca
        String url = "https://gutendex.com/books?search=" + termoBusca.replace(" ", "+");
        System.out.println("URL construída: " + url);

        // Cria o cliente HTTP e a requisição
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            // Envia a requisição e obtém a resposta da API
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verifica o código de status da resposta
            System.out.println("Código de status da resposta: " + response.statusCode());

            // Formata o JSON da resposta
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonRespostaFormatada = gson.toJson(gson.fromJson(response.body(), Object.class));

            // Retorna o JSON da resposta formatado
            return jsonRespostaFormatada;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao acessar a API.");
        }

    }
}
