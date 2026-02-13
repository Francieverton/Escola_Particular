package dao;

import factory.ConnectionFactory;
import model.Professor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    Connection connection;

    public ProfessorDAO(){
        connection = ConnectionFactory.conectar();
    }

    public void save (Professor professor) throws IOException, SQLException {

        String sql = "INSERT INTO professor (id_pessoa, formacao, salario) VALUES (?,?,?)";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,professor.getId());
            stmt.setString(2,professor.getFormacao());
            stmt.setDouble(3,professor.getSalario());
            stmt.execute();
            stmt.close();

            System.out.println("Professor salvo com sucesso!");

        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public void delete (int id) throws IOException, SQLException {

        String sql = "DELETE FROM professor WHERE id_pessoa = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
            stmt.close();

            System.out.println("Professor deletado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update (Professor professor) throws IOException, SQLException{

        String sql = "UPDATE professor SET formacao = ?, salario = ? WHERE id_pessoa = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, professor.getFormacao());
            stmt.setDouble(2,professor.getSalario());
            stmt.setInt(3,professor.getId());
            stmt.execute();
            stmt.close();

            System.out.println("Professor com id: "+professor.getId()+ " atualizado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List read () throws IOException, SQLException {
        String sql = "SELECT * FROM professor ORDER BY id_pessoa ASC";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            List<Professor> professorList = new ArrayList<>();

            while (resultSet.next()){
                Professor p = new Professor(resultSet.getInt("id_pessoa"),
                       null,
                        null,
                        null,
                        null,
                        null,
                        resultSet.getString("formacao"),
                        resultSet.getDouble("salario")
                );

                professorList.add(p);
            }
            return professorList;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
