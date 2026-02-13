package dao;

import factory.ConnectionFactory;
import model.Aluno;
import model.Disciplina;
import model.Nota;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaDAO {

    Connection connection;

    public NotaDAO(){connection = ConnectionFactory.conectar();}

    public void save(Nota nota) throws IOException, SQLException {

        String sql = "INSERT INTO nota (id_aluno, id_disciplina, valor_nota, bimestre) VALUES (?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1,nota.getAluno().getId());
            stmt.setInt(2,nota.getDisciplina().getId());
            stmt.setDouble(3,nota.getNota());
            stmt.setInt(4,nota.getBimestre());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public void delete (int id) throws IOException, SQLException{

        String sql = "DELETE FROM nota WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1,id);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update (Nota nota) throws IOException, SQLException {

        String sql = "UPDATE nota SET bimestre = ?, valor_nota = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1,nota.getBimestre());
            stmt.setDouble(2,nota.getNota());
            stmt.setInt(3,nota.getId());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            throw new SQLException(e);
        }
    }


    public List read () throws IOException, SQLException{

        String sql = "SELECT * FROM nota ORDER BY id ASC";

        AlunoDAO alunoDAO = new AlunoDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            List<Nota> notaList = new ArrayList<>();

            while (resultSet.next()){
                int idAluno = resultSet.getInt("id_aluno");
                int idDisciplina = resultSet.getInt("id_disciplina");

                Aluno alunoBanco = alunoDAO.findById(idAluno);
                Disciplina disciplinaBanco = disciplinaDAO.findById(idDisciplina);

                Nota nota = new Nota(resultSet.getInt("id"),
                        resultSet.getInt("bimestre"),
                        alunoBanco,
                        disciplinaBanco,
                        resultSet.getDouble("valor_nota")
                );
                notaList.add(nota);
            }
            return notaList;

        } catch (RuntimeException e) {
            throw new SQLException(e);
        }
    }

}
