package app;

import strategy.Disciplina;
import strategy.Aritmetica;
import strategy.Geometrica;
import strategy.MediaStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o tipo de cálculo de média:");
        System.out.println("1 - Média Aritmética (aprovado se média >= 5.0)");
        System.out.println("2 - Média Geométrica (aprovado se média >= 7.0)");
        System.out.print("Sua escolha: ");
        int escolha = scanner.nextInt();

        MediaStrategy estrategia;
        if (escolha == 1) {
            estrategia = new Aritmetica();
        } else if (escolha == 2) {
            estrategia = new Geometrica();
        } else {
            System.out.println("Opção inválida. Usando Média Aritmética por padrão.");
            estrategia = new Aritmetica();
        }

        Disciplina d = new Disciplina(estrategia);

        scanner.nextLine(); // consumir nova linha
        System.out.print("Nome da disciplina: ");
        d.setNome(scanner.nextLine());

        System.out.print("Nota P1: ");
        d.setP1(scanner.nextDouble());

        System.out.print("Nota P2: ");
        d.setP2(scanner.nextDouble());

        d.calcularMedia();

        System.out.printf("\nDisciplina: %s\nP1: %.2f  P2: %.2f  Média: %.2f  Situação: %s%n",
                d.getNome(), d.getP1(), d.getP2(), d.getMedia(), d.getSituacao());

        scanner.close();
    }
}
