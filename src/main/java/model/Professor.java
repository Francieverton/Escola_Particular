package model;

import java.time.LocalDate;

public class Professor extends Pessoa{

    private String formacao;
    private double salario;

    public Professor(int id, String nome, String cpf, String email, String telefone, LocalDate dataNadcimento, String formacao, double salario) {
        super(id, nome, cpf, email, telefone, dataNadcimento);
        this.formacao = formacao;
        this.salario = salario;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
