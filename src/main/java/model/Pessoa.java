package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Pessoa {

    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNadcimento;

    public Pessoa(int id, String nome, String cpf, String email, String telefone, LocalDate dataNadcimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataNadcimento = dataNadcimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNadcimento() {
        return dataNadcimento;
    }

    public void setDataNadcimento(LocalDate dataNadcimento) {
        this.dataNadcimento = dataNadcimento;
    }
}
