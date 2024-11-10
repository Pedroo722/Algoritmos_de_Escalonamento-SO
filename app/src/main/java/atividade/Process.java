package atividade;

public class Process {
    private final int id;
    private final int tempoChegada;
    private final int tempoExecucao;
    private int tempoRestante;
    private int tempoRetorno;
    private int tempoFinalizacao;
    private int prioridade;

    public Process(int id, int tempoChegada, int tempoExecucao, int prioridade) {
        this.id = id;
        this.tempoChegada = tempoChegada;
        this.tempoExecucao = tempoExecucao;
        this.tempoRestante = tempoExecucao;
        this.prioridade = prioridade;
    }

    public int getId() {
        return id;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public int getTempoExecucao() {
        return tempoExecucao;
    }

    public int getTempoRestante() {
        return tempoRestante;
    }

    public void decrementaTempoRestante() {
        this.tempoRestante--;
    }

    public void setTempoRetorno(int tempoRetorno) {
        this.tempoRetorno = tempoRetorno;
    }

    public int getTempoRetorno() {
        return tempoRetorno;
    }

    public void setTempoFinalizacao(int tempoFinalizacao) {
        this.tempoFinalizacao = tempoFinalizacao;
    }

    public int getTempoFinalizacao() {
        return tempoFinalizacao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}