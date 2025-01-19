package br.com.alura.conversor_de_moedas.main;

import java.util.Map;

public record MoedaApi(String base_code, Map<String, Double> conversion_rates) {
}
