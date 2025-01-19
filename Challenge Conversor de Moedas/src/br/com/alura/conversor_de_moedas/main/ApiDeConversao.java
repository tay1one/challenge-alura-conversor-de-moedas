package br.com.alura.conversor_de_moedas.main;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiDeConversao {
    private static final String CHAVE_API = "e2189915bb6ae2376283927e";
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/";

    public br.com.alura.conversor_de_moedas.main.MoedaApi buscarTaxasDeCambio(String codigoBase) {
        String url = URL_BASE + CHAVE_API + "/latest/" + codigoBase;
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest requisicao = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(resposta.body(), br.com.alura.conversor_de_moedas.main.MoedaApi.class);
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao conectar API: " + e.getMessage());
            return null;
        }
    }
}
