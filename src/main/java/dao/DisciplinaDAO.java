package dao;

import factory.ConnectionFactory;
import model.Disciplina;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    Connection connection;

    public DisciplinaDAO() {connection = ConnectionFactory.conectar();}

    public void save(Disciplina disciplina) throws IOException, SQLException{

        String sql = "INSERT INTO disciplina (nome, carga_horaria, ementa) VALUES (?,?,?)";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2,disciplina.getCargaHoraria());
            stmt.setString(3,disciplina.getEmenta());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete (int id) throws IOException, SQLException{

        String sql = "DELETE FROM disciplina WHERE id = ?";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1,id);
            stmt.execute();
            stmt.close();
        }
        catch (SQLException e){
            throw new SQLException();
        }
    }


    public void update (Disciplina disciplina) throws IOException, SQLException{

        String sql = "UPDATE disciplina SET nome = ?, carga_horaria = ?, ementa = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2,disciplina.getCargaHoraria());
            stmt.setString(3,disciplina.getEmenta());
            stmt.setInt(4,disciplina.getId());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }


    public List read() throws IOException, SQLException{

        String sql = "SELECT * FROM disciplina ORDER BY id ASC";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            List<Disciplina> disciplinaList = new ArrayList<>();

            while (resultSet.next()){
                Disciplina disciplina = new Disciplina(resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("ementa"),
                        resultSet.getInt("carga_horaria")
                );
                disciplinaList.add(disciplina);
            }
            return disciplinaList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Disciplina findById(int id) throws SQLException {
        String sql = "SELECT * FROM disciplina WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new Disciplina(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("ementa"),
                        resultSet.getInt("carga_horaria")
                );
            }
        }
        return null;
    }
}
