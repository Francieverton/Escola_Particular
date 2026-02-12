import dao.AlunoDAO;
import dao.PessoaDAO;
import dao.ProfessorDAO;
import factory.ConnectionFactory;
import model.Aluno;
import model.Pessoa;
import model.Professor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            AlunoDAO alunoDAO = new AlunoDAO();

            Aluno joao = new Aluno(20,"jozezinho", "098","joze@gmail.com", "098098907",
                    LocalDate.of(1900,3,24), "99999999",LocalDate.of(2024,2,21),
                    "Pago", );



        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
