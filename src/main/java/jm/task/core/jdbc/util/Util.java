package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/my_db";
    private final String USER = "root";
    private final String PASSWORD = "root";

    private Connection conn;


    public Util() {
        try{
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() throws SQLException {
        conn.close();
    }
    // реализуйте настройку соеденения с БД
}
