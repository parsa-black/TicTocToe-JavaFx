package com.company.Controller;

import java.sql.*;

public class PlayerDB {

    static final String DB_URL = "jdbc:mysql://localhost:3306/tiktoktoe-project" ;
    static final String USER = "root";
    static final String PASS = "parsagorgzan1225@gmail";
    private static Connection connection;
    private static Statement statement;


    public static void makeConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
