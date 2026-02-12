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
                AlunoDAO alunoDAO = new AlunoDAO();

                List<Aluno> alunoList = alunoDAO.read();

                for (int k =0; k < alunoList.size();k++){
                    System.out.println(alunoList.get(k));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
