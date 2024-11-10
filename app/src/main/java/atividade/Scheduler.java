package atividade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Scheduler {
    private final List<Process> processos;
    private int tempoTroca; // 2 no Main

    public Scheduler(List<Process> processos, int tempoTroca) {
        this.processos = processos;
        this.tempoTroca = tempoTroca;
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
            tempoAtual += p.getTempoExecucao();
        }
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
        }
    }

    // Escalonamento RR - Round Robin
    // public void rr(int quantum) {
    //     Queue<Process> fila = new LinkedList<>();
    //     int tempoAtual = 0;

    //     while (!processos.isEmpty() || !fila.isEmpty()) {
    //         for (Process p : processos) {
    //             if (p.getTempoChegada() <= tempoAtual) {
    //                 fila.add(p);
    //             }
    //         }

    //         if (!fila.isEmpty()) {
    //             Process p = fila.poll();
    //             int tempoExecucao = Math.min(p.getTempoRestante(), quantum);
    //             tempoAtual += tempoExecucao;
    //             p.decrementaTempoRestante();
    //             if (p.getTempoRestante() == 0) {
    //                 p.setTempoFinalizacao(tempoAtual);
    //                 p.setTempoRetorno(tempoAtual - p.getTempoChegada());
    //             } else {
    //                 fila.add(p);
    //             }
    //         }
    //     }
    // }

    // Escalonamento por Prioridade
    public void prioridade() {
        // menor número de prioridade = executado primeiro
        processos.sort(Comparator.comparingInt(Process::getPrioridade));

        int tempoAtual = 0;
        for (Process p : processos) {
            if (tempoAtual < p.getTempoChegada()) {
                tempoAtual = p.getTempoChegada();
            }
            p.setTempoFinalizacao(tempoAtual + p.getTempoExecucao());
            p.setTempoRetorno(p.getTempoFinalizacao() - p.getTempoChegada());
            tempoAtual += p.getTempoExecucao();
        }
    }

    // Escalonamento LOT - Loteria
    public void lot() {
        Random random = new Random();
        List<Process> processosSorteados = new ArrayList<>(processos);
        Collections.shuffle(processosSorteados, random);

        int tempoAtual = 0;
        for (Process p : processosSorteados) {
            if (tempoAtual < p.getTempoChegada()) {
                tempoAtual = p.getTempoChegada();
            }
            p.setTempoFinalizacao(tempoAtual + p.getTempoExecucao());
            p.setTempoRetorno(p.getTempoFinalizacao() - p.getTempoChegada());
            tempoAtual += p.getTempoExecucao();
        }
    }
}
