package model;

import java.time.LocalDate;

public class Responsavel extends Pessoa {
    private String profissao;
    private String parentesco;

    public Responsavel(int id, String nome, String cpf, String email, String telefone, LocalDate dataNascimento, String profissao, String parentesco) {
        super(id, nome, cpf, email, telefone, dataNascimento);
        this.profissao = profissao;
        this.parentesco = parentesco;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
}
