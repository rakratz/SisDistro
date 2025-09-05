package client;
import api.Calculadora;
import config.RmiConfig;

import java.rmi.Naming;
import java.util.Scanner;

public class Cliente2 {
    public static void main(String[] args) {
        try {
            Calculadora calc = (Calculadora) Naming.lookup(RmiConfig.serviceUrl());
            Scanner sc = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("\n=== CALCULADORA RMI ===");
                System.out.println("1 - Soma (+)");
                System.out.println("2 - Subtração (-)");
                System.out.println("3 - Multiplicação (*)");
                System.out.println("4 - Divisão (/)");
                System.out.println("0 - Sair");
                System.out.print("Escolha a opção: ");
                opcao = sc.nextInt();

                if (opcao >= 1 && opcao <= 4) {
                    System.out.print("Digite o valor de a: ");
                    int a = sc.nextInt();
                    System.out.print("Digite o valor de b: ");
                    int b = sc.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.println("Resultado: " + calc.soma(a, b));
                            break;
                        case 2:
                            System.out.println("Resultado: " + calc.subtrai(a, b));
                            break;
                        case 3:
                            System.out.println("Resultado: " + calc.multiplica(a, b));
                            break;
                        case 4:
                            if (b != 0) {
                                System.out.println("Resultado: " + calc.divide(a, b));
                            } else {
                                System.out.println("Erro: divisão por zero!");
                            }
                            break;
                    }
                } else if (opcao != 0) {
                    System.out.println("Opção inválida!");
                }

            } while (opcao != 0);

            System.out.println("Encerrando a calculadora...");
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
