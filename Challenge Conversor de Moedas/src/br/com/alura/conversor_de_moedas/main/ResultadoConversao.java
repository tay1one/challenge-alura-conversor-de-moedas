package br.com.alura.conversor_de_moedas.main;

public class ResultadoConversao {
    private final String moedaOrigem;
    private final String moedaDestino;
    private final double valor;
    private final double taxaDeCambio;

    public ResultadoConversao(br.com.alura.conversor_de_moedas.main.MoedaApi moedaApi, double valor, String moedaDestino) {
        this.moedaOrigem = moedaApi.base_code();
        this.moedaDestino = moedaDestino;
        this.valor = valor;
        this.taxaDeCambio = moedaApi.conversion_rates().getOrDefault(moedaDestino, -1.0);

        if (this.taxaDeCambio < 0) {
            throw new IllegalArgumentException("Moeda de destino inválida: " + moedaDestino);
        }
    }

    public String formatarResultado() {
        double valorConvertido = valor * taxaDeCambio;
        return String.format("Valor %.2f [%s] equivale a %.2f [%s].", valor, moedaOrigem, valorConvertido, moedaDestino);
    }
}
