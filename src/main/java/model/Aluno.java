package model;

import java.time.LocalDate;

public class Aluno extends Pessoa{
    private String matricula;
    private LocalDate dataMatricula;
    private String status;
    private Responsavel responsavel;

    public Aluno(int id, String nome, String cpf, String email, String telefone, LocalDate dataNascimento, String matricula, LocalDate dataMatricula, String status, Responsavel responsavel) {
        super(id, nome, cpf, email, telefone, dataNascimento);
        this.matricula = matricula;
        this.dataMatricula = dataMatricula;
        this.status = status;
        this.responsavel = responsavel;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
}
