package br.com.alura.conversor_de_moedas.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cardapio {
    private final Scanner input = new Scanner(System.in);
    private final br.com.alura.conversor_de_moedas.main.ConversorDeMoedas conversor = new br.com.alura.conversor_de_moedas.main.ConversorDeMoedas();

    public void iniciar() {
        int opcao;

        do {
            exibirCardapio();
            opcao = obterOpcaoUsuario();
            processarOpcao(opcao);
        } while (opcao != 7);
    }

    private void exibirCardapio() {
        System.out.println("**************************************************");
        System.out.println("""
                Digite uma opção!
                1 - Dólar para Peso Argentino
                2 - Peso Argentino para Dólar
                3 - Real Brasileiro para Dólar
                4 - Dólar para Real Brasileiro
                5 - Dólar para Peso Colombiano
                6 - Peso Colombiano para Dólar
                7 - Encerrar
                """);
        System.out.println("**************************************************");
        System.out.println();
    }

    private int obterOpcaoUsuario() {
        System.out.print("Escolha uma opção: ");
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine(); // limpa entrada inválida
            System.out.println("Por favor, digite um número entre 1 e 7.");
            return -1;
        }
    }

    private void processarOpcao(int opcao) {
        if (opcao == 7) {
            System.out.println("Encerrado");
            return;
        }

        if (opcao < 1 || opcao > 7) {
            System.out.println("Inválida! Tente novamente.");
            return;
        }

        System.out.print("Digite o valor: ");
        double valor;
        try {
            valor = input.nextDouble();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Por favor, digite um número válido.");
            return;
        }

        String moedaOrigem = identificarMoedaOrigem(opcao);
        String moedaDestino = identificarMoedaDestino(opcao);

        if (moedaOrigem.isEmpty() || moedaDestino.isEmpty()) {
            System.out.println("Inválida! Tente novamente.");
            return;
        }

        String resultado = conversor.converterMoeda(moedaOrigem, moedaDestino, valor);
        System.out.println(resultado);
    }

    private String identificarMoedaOrigem(int opcao) {
        return switch (opcao) {
            case 1, 4, 5 -> "USD";
            case 2 -> "ARS";
            case 3 -> "BRL";
            case 6 -> "COP";
            default -> "";
        };
    }

    private String identificarMoedaDestino(int opcao) {
        return switch (opcao) {
            case 1 -> "ARS";
            case 2 -> "USD";
            case 3 -> "USD";
            case 4 -> "BRL";
            case 5 -> "COP";
            case 6 -> "USD";
            default -> "";
        };
    }
}