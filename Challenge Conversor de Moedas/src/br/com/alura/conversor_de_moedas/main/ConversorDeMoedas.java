package br.com.alura.conversor_de_moedas.main;

public class ConversorDeMoedas {
    private final br.com.alura.conversor_de_moedas.main.ApiDeConversao apiDeConversao = new br.com.alura.conversor_de_moedas.main.ApiDeConversao();

    public String converterMoeda(String moedaOrigem, String moedaDestino, double valor) {
        br.com.alura.conversor_de_moedas.main.MoedaApi moedaApi = apiDeConversao.buscarTaxasDeCambio(moedaOrigem);
        if (moedaApi == null) {
            return "Erro ao obter valores das taxas de câmbio. Tente novamente.";
        }

        br.com.alura.conversor_de_moedas.main.ResultadoConversao resultado = new br.com.alura.conversor_de_moedas.main.ResultadoConversao(moedaApi, valor, moedaDestino);
        return resultado.formatarResultado();
    }
}