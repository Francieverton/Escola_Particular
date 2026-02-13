package dao;

import factory.ConnectionFactory;
import model.Aluno;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    Connection connection;

    public AlunoDAO(){
        connection = ConnectionFactory.conectar();
    }

    public void save (Aluno aluno) throws IOException, SQLException {

        String sql = "INSERT INTO aluno (id_pessoa, matricula, data_matricula, status, " +
                "nome_responsavel, cpf_responsavel, telefone_responsavel) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1,aluno.getId());
            stmt.setString(2, aluno.getMatricula());
            stmt.setDate(3, Date.valueOf(aluno.getDataMatricula()));
            stmt.setString(4, aluno.getStatus());
            stmt.setString(5, aluno.getNomeResponsavel());
            stmt.setString(6, aluno.getCpfResponsavel());
            stmt.setString(7, aluno.getTelefoneResponsavel());
            stmt.execute();
            ResultSet resultSet = stmt.getGeneratedKeys();
            stmt.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete (int id) throws IOException, SQLException {

        String sql = "DELETE FROM aluno where id_pessoa = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update (Aluno aluno) throws IOException, SQLException{

        String sql = "UPDATE aluno SET matricula = ?, data_matricula = ?, status = ?, nome_responsavel = ?, cpf_responsavel = ?," +
                " telefone_responsavel = ? WHERE id_pessoa = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getMatricula());
            stmt.setDate(2,Date.valueOf(aluno.getDataMatricula()));
            stmt.setString(3, aluno.getStatus());
            stmt.setString(4, aluno.getNomeResponsavel());
            stmt.setString(5,aluno.getCpfResponsavel());
            stmt.setString(6,aluno.getTelefoneResponsavel());
            stmt.setInt(7,aluno.getId());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List read() throws IOException, SQLException {

        String sql = "SELECT * FROM aluno ORDER BY id_pessoa ASC";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            List<Aluno> alunoList = new ArrayList<>();

            while (resultSet.next()){

                Aluno aluno = new Aluno(resultSet.getInt("id_pessoa"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        resultSet.getString("matricula"),
                        resultSet.getDate("data_matricula").toLocalDate(),
                        resultSet.getString("status"),
                        resultSet.getString("nome_responsavel"),
                        resultSet.getString("cpf_responsavel"),
                        resultSet.getString("telefone_responsavel")
                );
                alunoList.add(aluno);
            }
            return alunoList;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public Aluno findById(int id) {
        String sql = "SELECT * FROM aluno INNER JOIN pessoa ON aluno.id_pessoa = pessoa.id WHERE aluno.id_pessoa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return new Aluno(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        resultSet.getDate("data_nascimento").toLocalDate(),
                        resultSet.getString("matricula"),
                        resultSet.getDate("data_matricula").toLocalDate(),
                        resultSet.getString("status"),
                        resultSet.getString("nome_responsavel"),
                        resultSet.getString("cpf_responsavel"),
                        resultSet.getString("telefone_responsavel")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno por ID", e);
        }
        return null;
    }
}
