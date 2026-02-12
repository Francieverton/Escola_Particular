package dao;

import factory.ConnectionFactory;
import model.Aluno;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO {

    Connection connection;

    public AlunoDAO(){
        connection = ConnectionFactory.conectar();
    }

    public void save (Aluno aluno) throws IOException, SQLException {

        String sql = "INSERT INTO aluno (id_pessoa, matricula, data_matricula, status, id_responsavel) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1,aluno.getId());
            stmt.setString(2, aluno.getMatricula());
            stmt.setDate(3, Date.valueOf(aluno.getDataMatricula()));
            stmt.setString(4, aluno.getStatus());
            stmt.setInt(5, aluno.getResponsavel().getId());
            stmt.execute();
            stmt.close();

            System.out.println("salvoooooo");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
