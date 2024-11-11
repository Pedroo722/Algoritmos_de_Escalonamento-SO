package atividade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Scheduler {
    private final List<Process> processos;
    private final int tempoTroca; // Troca de Contexto

    public Scheduler(List<Process> processos, int tempoTroca) {
        this.processos = processos;
        this.tempoTroca = tempoTroca;
    }

    // média de tempos e ociosidade do processador
    private void calculaMedias() {
        int somaTempoChegada = 0;
        int somaTempoRetorno = 0;
        int ociosidade = 0;
        
        for (int i = 0; i < processos.size(); i++) {
            Process p = processos.get(i);
            somaTempoChegada += p.getTempoChegada();
            somaTempoRetorno += p.getTempoRetorno();

            if (i > 0) { // calcula ociosidade entre processos
                Process anterior = processos.get(i - 1);
                if (anterior.getTempoFinalizacao() < p.getTempoChegada()) {
                    ociosidade += (p.getTempoChegada() - anterior.getTempoFinalizacao());
                } else {
                    ociosidade += tempoTroca;
                }
            }
        }

        double mediaChegada = somaTempoChegada / (double) processos.size();
        double mediaRetorno = somaTempoRetorno / (double) processos.size();

        System.out.println("\nMédia de Tempo de Chegada: " + mediaChegada);
        System.out.println("Média de Tempo de Retorno: " + mediaRetorno);
        System.out.println("Tempo de Ociosidade do Processador: " + ociosidade);
    }

    // Método de cálculo específico para RR
    private void calculaMediasRR(int totalTrocas) {
        int somaTempoChegada = 0;
        int somaTempoRetorno = 0;
        int ociosidade = totalTrocas * tempoTroca;

        for (Process p : processos) {
            somaTempoChegada += p.getTempoChegada();
            somaTempoRetorno += p.getTempoRetorno();
        }

        double mediaChegada = somaTempoChegada / (double) processos.size();
        double mediaRetorno = somaTempoRetorno / (double) processos.size();

        System.out.println("\nMédia de Tempo de Chegada: " + mediaChegada);
        System.out.println("Média de Tempo de Retorno: " + mediaRetorno);
        System.out.println("Tempo de Ociosidade do Processador: " + ociosidade);
    }

    // Método de cálculo específico para LOT (Loteria)
    private void calculaMediasLoteria(int totalTrocas) {
        int somaTempoChegada = 0;
        int somaTempoRetorno = 0;
        int ociosidade = totalTrocas * tempoTroca;

        for (Process p : processos) {
            somaTempoChegada += p.getTempoChegada();
            somaTempoRetorno += p.getTempoRetorno();
        }

        double mediaChegada = (double) somaTempoChegada / processos.size();
        double mediaRetorno = (double) somaTempoRetorno / processos.size();

        System.out.println("\nMédia de Tempo de Chegada: " + mediaChegada);
        System.out.println("Média de Tempo de Retorno: " + mediaRetorno);
        System.out.println("Tempo de Ociosidade do Processador: " + ociosidade);
    }

    // Escalonamento FIFO - First In First Out
    public void fifo() {
        Collections.sort(processos, Comparator.comparingInt(Process::getTempoChegada));
    
        int tempoAtual = 0;
        for (Process p : processos) {
            if (tempoAtual < p.getTempoChegada()) {
                tempoAtual = p.getTempoChegada();
            }
            p.setTempoFinalizacao(tempoAtual + p.getTempoExecucao());
            p.setTempoRetorno(p.getTempoFinalizacao() - p.getTempoChegada());
            tempoAtual += p.getTempoExecucao() + tempoTroca;
    
            // Imprimir tempo de retorno e finalização de cada processo
            System.out.println("Processo " + p.getId() +
                               " | Tempo de Retorno: " + p.getTempoRetorno() +
                               " | Tempo de Finalização: " + p.getTempoFinalizacao());
        }
    
        calculaMedias();
    }
    
    // Escalonamento SJF - Shortest Job First
    public void sjf() {
        processos.sort(Comparator.comparingInt(Process::getTempoExecucao));

        int tempoAtual = 0;
        for (Process p : processos) {
            if (tempoAtual < p.getTempoChegada()) {
                tempoAtual = p.getTempoChegada();
            }
            p.setTempoFinalizacao(tempoAtual + p.getTempoExecucao());
            p.setTempoRetorno(p.getTempoFinalizacao() - p.getTempoChegada());
            tempoAtual += p.getTempoExecucao();

            // Imprimir tempo de retorno e finalização de cada processo
            System.out.println("Processo " + p.getId() +
                            " | Tempo de Retorno: " + p.getTempoRetorno() +
                            " | Tempo de Finalização: " + p.getTempoFinalizacao());
        }

        calculaMedias();
    }

    // Escalonamento RR - Round Robin
    public void rr(int fatiaTempo) {
        Queue<Process> fila = new LinkedList<>(processos);
        int tempoAtual = 0;
        int totalTrocas = 0;

        while (!fila.isEmpty()) {
            Process p = fila.poll();

            if (tempoAtual < p.getTempoChegada()) {
                tempoAtual = p.getTempoChegada();
            }

            int tempoExecucao = Math.min(p.getTempoRestante(), fatiaTempo);
            tempoAtual += tempoExecucao;
            p.decrementaTempoRestante(tempoExecucao);

            if (p.getTempoRestante() > 0) {
                fila.add(p);
                tempoAtual += tempoTroca;
                totalTrocas++;
            } else {
                p.setTempoFinalizacao(tempoAtual);
                p.setTempoRetorno(p.getTempoFinalizacao() - p.getTempoChegada());
                System.out.println("Processo " + p.getId() +
                        " | Tempo de Retorno: " + p.getTempoRetorno() +
                        " | Tempo de Finalização: " + p.getTempoFinalizacao());
                if (!fila.isEmpty()) {
                    tempoAtual += tempoTroca;
                    totalTrocas++;
                }
            }
        }

        calculaMediasRR(totalTrocas);
    }


    // Escalonamento por Prioridade
    public void prioridade() {
        // menor número de prioridade = executado primeiro)
        processos.sort(Comparator.comparingInt(Process::getPrioridade));

        int tempoAtual = 0;
        for (Process p : processos) {
            // Se o tempo atual for menor que o tempo de chegada, ajusta
            if (tempoAtual < p.getTempoChegada()) {
                tempoAtual = p.getTempoChegada();
            }
            
            // Calcula o tempo de finalização e retorno
            p.setTempoFinalizacao(tempoAtual + p.getTempoExecucao());
            p.setTempoRetorno(p.getTempoFinalizacao() - p.getTempoChegada());
            tempoAtual += p.getTempoExecucao();

            // Imprime o tempo de retorno e finalização de cada processo
            System.out.println("Processo " + p.getId() +
                    " | Tempo de Retorno: " + p.getTempoRetorno() +
                    " | Tempo de Finalização: " + p.getTempoFinalizacao());
        }

        calculaMedias();
    }

    // Escalonamento LOT - Loteria
    public void lot() {
        Random random = new Random();
        int tempoAtual = 0;
        int totalTrocas = 0; // Contador para trocas de contexto
        List<Process> processosExecutados = new ArrayList<>(processos); // Cria uma cópia para manter os dados

        while (!processosExecutados.isEmpty()) {
            // Escolher um processo aleatoriamente
            Process p = processosExecutados.get(random.nextInt(processosExecutados.size()));

            // Se o tempo atual for menor que o tempo de chegada, ajusta
            if (tempoAtual < p.getTempoChegada()) {
                tempoAtual = p.getTempoChegada();
            }

            // Calcula o tempo de finalização e retorno
            p.setTempoFinalizacao(tempoAtual + p.getTempoExecucao());
            p.setTempoRetorno(p.getTempoFinalizacao() - p.getTempoChegada());
            tempoAtual += p.getTempoExecucao();

            // Imprime o tempo de retorno e finalização de cada processo
            System.out.println("Processo " + p.getId() +
                    " | Tempo de Retorno: " + p.getTempoRetorno() +
                    " | Tempo de Finalização: " + p.getTempoFinalizacao());

            // Remove o processo da lista após ser executado
            processosExecutados.remove(p);

            // Conta a troca de contexto realizada
            if (!processosExecutados.isEmpty()) {
                tempoAtual += tempoTroca;
                totalTrocas++;
            }
        }

        // Chama o método de cálculo das médias específicas para o escalonamento LOT
        calculaMediasLoteria(totalTrocas);
    }
}
