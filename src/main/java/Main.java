import factory.ConnectionFactory;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection connection;

        connection = ConnectionFactory.conectar();

        if (connection != null){
            System.out.println("Conectou!");
        }
        else {
            System.out.println("Não conectou");
        }
    }
}
