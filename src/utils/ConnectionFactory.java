package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection()
            throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexao =
                    DriverManager.getConnection(
                            "jdbc:mysql://dbeventos.chrloaatkzds.us-east-2.rds.amazonaws.com:3306/dbeventos", "root", "chicootaku");
            return conexao;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}