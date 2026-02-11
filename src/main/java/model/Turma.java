package model;

public class Turma {
    private int id;
    private String codigoTurma;
    private String turno;
    private int anoLetivo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public Turma(int id, String codigoTurma, String turno, int anoLetivo) {
        this.id = id;
        this.codigoTurma = codigoTurma;
        this.turno = turno;
        this.anoLetivo = anoLetivo;
    }
}
