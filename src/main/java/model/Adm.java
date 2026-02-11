package model;

import java.time.LocalDate;

public class Adm extends Pessoa{
    private String setor;
    private String cargo;
    private int nivelAcesso;

    public Adm(int id, String nome, String cpf, String email, String telefone, LocalDate dataNascimento, String setor, String cargo, int nivelAcesso) {
        super(id, nome, cpf, email, telefone, dataNascimento);
        this.setor = setor;
        this.cargo = cargo;
        this.nivelAcesso = nivelAcesso;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}
