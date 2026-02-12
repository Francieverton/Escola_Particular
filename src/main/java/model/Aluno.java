package model;

import java.time.LocalDate;

public class Aluno extends Pessoa{
    private String matricula;
    private LocalDate dataMatricula;
    private String status;
    private String nomeResponsavel;
    private String cpfResponsavel;
    private String telefoneResponsavel;

    public Aluno(int id, String nome, String cpf, String email, String telefone, LocalDate dataNascimento,
                 String matricula,
                 LocalDate dataMatricula, String status,
                 String nomeResponsavel, String cpfResponsavel, String telefoneResponsavel) {
        super(id, nome, cpf, email, telefone, dataNascimento);
        this.matricula = matricula;
        this.dataMatricula = dataMatricula;
        this.status = status;
        this.nomeResponsavel = nomeResponsavel;
        this.cpfResponsavel = cpfResponsavel;
        this.telefoneResponsavel = telefoneResponsavel;
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

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "matricula='" + matricula + '\'' +
                ", dataMatricula=" + dataMatricula +
                ", status='" + status + '\'' +
                ", nomeResponsavel='" + nomeResponsavel + '\'' +
                ", cpfResponsavel='" + cpfResponsavel + '\'' +
                ", telefoneResponsavel='" + telefoneResponsavel + '\'' +
                '}';
    }
}
