package com.company.Controller;

import com.company.Model.Player;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MultiPlayPageController implements Initializable {

    @FXML
    private ChoiceBox choiceBOX;

    @FXML
    private Button backBTN;

    @FXML
    private Button playBTN;

    @FXML
    private TextField player1TF;

    @FXML
    private TextField player2TF;

    @FXML
    private Label checkLBL;

    static Stage stage;

    private int score = 0;

    private boolean Verify = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        checkLBL.setText("");

        backBTN.setOnAction(e -> {
            ((Stage) backBTN.getScene().getWindow()).close();
            try {
                if (stage == null) {
                    AnchorPane root = FXMLLoader.load(this.getClass().getResource("../View/PlayPageView.fxml"));
                    stage = new Stage();
                    stage.setTitle("PlayPage");
                    stage.setScene(new Scene(root));
                    stage.show();
                    stage = null;
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        playBTN.setOnAction(e -> {
            OpenPlayPage();
            if (Verify){
                Player player1 = getUserWithUserName(player1TF.getText());
                Player player2 = getUserWithUserName(player2TF.getText());
                try {
                    loadPlayPage(player1, player2);
                    cleanPage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        choiceBOX.getItems().add("choice 1");
        choiceBOX.getItems().add("choice 2");
        choiceBOX.getItems().add("choice 3");


    }

    private void OpenPlayPage() {
        if (checkAllFiled()) {
            Player player1 = getUserWithUserName(player1TF.getText());
            Player player2 = getUserWithUserName(player2TF.getText());
            if (player1 != null && player2 != null) {
                Verify = true;
            } else if (player1 == null && player2 == null) {
                CreatePlayer1();
                CreatePlayer2();
                Verify = true;
            } else {
                if (player1 == null) {
                    CreatePlayer1();
                } else {
                    CreatePlayer2();
                }
                Verify = true;
            }
        }
    }

    private boolean checkAllFiled() {
        if (player1TF.getText().isEmpty() || player2TF.getText().isEmpty()) {
            checkLBL.setText("Please Fill Up The Form");
            return false;
        }
        return true;
    }

    private Player getUserWithUserName(String userName) {
        ArrayList<Player> players = Player.getAllPlayers();

        for (Player player : players) {
            if (player.getUsername().equals(userName))
                return player;
        }
        return null;
    }

    private void loadPlayPage(Player player1, Player player2) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/GamePageView.fxml"));
        loader.load();
        GamePageController controller = loader.getController();
        controller.initPage(player1, player2);
        Stage stage = (Stage) playBTN.getScene().getWindow();
        stage.setScene(new Scene(loader.getRoot()));
        stage.show();

    }

    private void CreatePlayer1() {
        Player player = new Player(player1TF.getText(), score);
        player.save();
    }


    private void CreatePlayer2() {
        Player player = new Player(player2TF.getText(), score);
        player.save();
    }


    private void cleanPage() {
        Verify = false;
        player1TF.setText("");
        player2TF.setText("");
        checkLBL.setText("");
    }

}
