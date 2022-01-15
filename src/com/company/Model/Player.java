package com.company.Model;

import com.company.Controller.PlayerDB;

import java.sql.SQLException;
import java.util.ArrayList;


public class Player {

    private int id = -1;
    private String username;
    private float score ;

    public Player(int id, String username, float score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    public Player(String username , float score) {
        this.username = username;
        this.score = score;
    }

    public static ArrayList<Player> getAllPlayers(){
        try {
            return PlayerDB.getAllPlayers();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void save(){ //save in DataBase
        if (this.id == -1){
            try {
                id = PlayerDB.createUser(this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PlayerDB.editUser(this); //Update in database
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } //save => create or edit







    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
