package model;

import java.time.LocalDate;

public class Mensalidade {
    private int id;
    private Aluno aluno;
    private double valorTotal;
    private double valorPago;
    private LocalDate dataVencimento;
    private String statusPagamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Mensalidade(int id, Aluno aluno, double valorTotal, double valorPago, LocalDate dataVencimento, String statusPagamento) {
        this.id = id;
        this.aluno = aluno;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.dataVencimento = dataVencimento;
        this.statusPagamento = statusPagamento;
    }
}
