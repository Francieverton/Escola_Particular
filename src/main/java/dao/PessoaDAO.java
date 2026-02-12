package dao;

import factory.ConnectionFactory;
import model.Pessoa;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class PessoaDAO {
    Connection connection;

    public PessoaDAO() {
        connection = (Connection) ConnectionFactory.conectar();
    }

    public void save (Pessoa pessoa) throws IOException, SQLException {
        String sql = "INSERT INTO Pessoa (nome,cpf,data_nascimento,email,telefone) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setDate(3, Date.valueOf(pessoa.getDataNascimento()));
            stmt.setString(4, pessoa.getEmail());
            stmt.setString(5, pessoa.getTelefone());
            stmt.execute();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                int idGerado = resultSet.getInt(1);
                pessoa.setId(idGerado);
            }
            stmt.close();

            System.out.println("Pessoa salva com sucesso! id: "+ pessoa.getId());
        }
        catch (RuntimeException e){
            System.out.println("Erro em alguma informação: " + e.getMessage());
        }
    }

    public void delete (int id) throws IOException, SQLException {

        String sql = "DELETE FROM pessoa WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        }
        catch (SQLException e){
            System.out.println("Id não encontrado.");
        }
    }

    public void update (Pessoa pessoa) {

        String sql = "UPDATE pessoa SET nome = ?, cpf = ?, data_nascimento = ?, " +
                "email = ?, telefone = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setDate(3, Date.valueOf(pessoa.getDataNascimento()));
            stmt.setString(4, pessoa.getEmail());
            stmt.setString(5, pessoa.getTelefone());
            stmt.setInt(6,pessoa.getId());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pessoa> read () throws IOException, SQLException {
        String sql = "SELECT * FROM pessoa";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            List<Pessoa> pessoaList = new ArrayList<>();

            while (resultSet.next()){
                Pessoa pessoa = new Pessoa(resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        resultSet.getDate("data_nascimento").toLocalDate()
                );

                pessoaList.add(pessoa);
            }
            return pessoaList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
