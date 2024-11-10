package atividade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        List<Process> processos = new ArrayList<>();
        
        // Fila de Processos com prioridade
        processos.add(new Process(1, 0, 5, 7));
        processos.add(new Process(2, 1, 3, 3));
        processos.add(new Process(3, 2, 8, 15));
        
        int tempoTroca = 2;
        Scheduler scheduler = new Scheduler(processos, tempoTroca);

        // Mostrar a fila de processos
        System.out.println("\n== Fila de Processos ==");
        for (Process p : processos) {
            System.out.println("Processo " + p.getId() + " | Tempo Chegada: " + p.getTempoChegada() + " | Tempo Execução: " + p.getTempoExecucao() + " | Prioridade: " + p.getPrioridade());
        }


        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            // Menu
            System.out.println("\nEscolha um algoritmo de escalonamento:");
            System.out.println("1 - FIFO");
            System.out.println("2 - SJF");
            System.out.println("3 - RR");
            System.out.println("4 - Prioridade");
            System.out.println("5 - LOT (Loteria)");
            System.out.println("0 - Sair");

            System.out.print("\nOpção: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    // Executar FIFO
                    System.out.println("\n== Escalonamento por FIFO ==");
                    scheduler.fifo();
                    for (Process p : processos) {
                        System.out.println("Processo " + p.getId() + ": Tempo Retorno = " + p.getTempoRetorno() + ", Tempo Finalizacao = " + p.getTempoFinalizacao());
                    }
                    break;
                case 2:
                    // Executar SJF
                    System.out.println("\n== Escalonamento por SJF ==");
                    scheduler.sjf();
                    for (Process p : processos) {
                        System.out.println("Processo " + p.getId() + ": Tempo Retorno = " + p.getTempoRetorno() + ", Tempo Finalizacao = " + p.getTempoFinalizacao());
                    }
                    break;
                case 3:
                    // Executar RR
                    System.out.println("\n== Escalonamento por RR ==");
                    System.out.println("Em implementação");
                    // scheduler.rr(2);
                    // for (Process p : processos) {
                    //     System.out.println("Processo " + p.getId() + ": Tempo Retorno = " + p.getTempoRetorno() + ", Tempo Finalizacao = " + p.getTempoFinalizacao());
                    // }
                    break;
                case 4:
                    // Executar Escalonamento por Prioridade
                    System.out.println("\n== Escalonamento por Prioridade ==");
                    scheduler.prioridade();
                    for (Process p : processos) {
                        System.out.println("Processo " + p.getId() + ": Tempo Retorno = " + p.getTempoRetorno() + ", Tempo Finalizacao = " + p.getTempoFinalizacao());
                    }
                    break;
                case 5:
                    // Executar Escalonamento LOT (Loteria)
                    System.out.println("\n== Escalonamento por LOT (Loteria) ==");
                    scheduler.lot();
                    for (Process p : processos) {
                        System.out.println("Processo " + p.getId() + ": Tempo Retorno = " + p.getTempoRetorno() + ", Tempo Finalizacao = " + p.getTempoFinalizacao());
                    }
                    break;
                case 0:
                    // Sair
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 0);

        scanner.close();
    }
}