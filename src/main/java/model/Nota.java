package model;

public class Nota {
    private int id;
    private int bimestre;
    private Aluno aluno;
    private Disciplina disciplina;
    private double nota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBimestre() {
        return bimestre;
    }

    public void setBimestre(int bimestre) {
        this.bimestre = bimestre;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Nota(int id, int bimestre, Aluno aluno, Disciplina disciplina, double nota) {
        this.id = id;
        this.bimestre = bimestre;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.nota = nota;
    }
}
