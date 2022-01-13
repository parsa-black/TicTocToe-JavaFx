package com.company.Controller;

import com.company.Model.Player;

import java.sql.*;
import java.util.ArrayList;

public class PlayerDB {

    static final String DB_URL = "jdbc:mysql://localhost:3306/tiktoktoe-project";
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

    public static void closeConnection() {
        if (connection != null) {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static ArrayList<Player> getAllPlayers() throws SQLException {

        makeConnection();

        ResultSet resultSet = statement.executeQuery("select * from players");

        ArrayList<Player> players = new ArrayList<>();

        while (resultSet.next()) {
            players.add(new Player(resultSet.getInt("id"), resultSet.getString("username"),
                    resultSet.getInt("score")));
        }

        closeConnection();

        return players;

    }

    public static int createUser(Player player) throws SQLException {

        makeConnection();
        statement.execute(String.format("INSERT INTO players (username , score ) value ( '%s' , %d )",
                        player.getUsername() , player.getScore() ),
                Statement.RETURN_GENERATED_KEYS);

        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        int id = resultSet.getInt(1);

        closeConnection();

        return id;

    }

    public static void editUser(Player player) throws SQLException { //Update in sql
        makeConnection();

        statement.execute(String.format(" UPDATE players SET username = '%s' , score = %d WHERE id = %d ", player.getUsername(), player.getScore(), player.getId()));

        closeConnection();

    }


}
