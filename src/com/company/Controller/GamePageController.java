package com.company.Controller;

import com.company.Model.Player;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {


    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Label statusLBL;

    @FXML
    private Label player1LBL;

    @FXML
    private Label player2LBL;

    @FXML
    private Button reStartBTN;

    @FXML
    private Label player1Score;

    @FXML
    private Label player2Score;

    private int p1s = 0; //Player1Score

    private int p2s = 0; //Player2Score


    private int playerTurn = 0;

    ArrayList<Button> buttons;

    private Player player1;

    private Player player2;

    private int counter = 1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        buttons.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });

        reStartBTN.setOnAction(e -> {
            buttons.forEach(this::resetButton);
            p1s = 0;
            p2s = 0;
            counter = 1;
            checkIfGameIsOver();
        });


    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    private void stopGame() {
        reStartBTN.setDisable(false);
        buttons.forEach(this::setButton);
    }

    private void setButton(Button button) {
        button.setDisable(true);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }

    public void setPlayerSymbol(Button button) {
        if (playerTurn % 2 == 0) {
            button.setText("X");
            playerTurn = 1;
        } else {
            button.setText("O");
            playerTurn = 0;
        }
    }

    public void checkIfGameIsOver() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button4.getText() + button5.getText() + button6.getText();
                case 2 -> button7.getText() + button8.getText() + button9.getText();
                case 3 -> button1.getText() + button5.getText() + button9.getText();
                case 4 -> button3.getText() + button5.getText() + button7.getText();
                case 5 -> button1.getText() + button4.getText() + button7.getText();
                case 6 -> button2.getText() + button5.getText() + button8.getText();
                case 7 -> button3.getText() + button6.getText() + button9.getText();
                default -> null;
            };
            //X winner
            if (line.equals("XXX")) {
                counter++;
                if (counter > 3) {
                    statusLBL.setText(player1.getUsername() + " Won");
                    stopGame();
                } else {
                    p1s++;
                    player1Score.setText(Integer.toString(p1s));
                    buttons.forEach(this::resetButton);
                    statusLBL.setText("Round " + counter);
                }
            }
            //O winner
            else if (line.equals("OOO")) {
                counter++;
                if (counter > 3) {
                    statusLBL.setText(player2.getUsername() + " Won");
                    stopGame();
                } else {
                    p2s++;
                    player2Score.setText(Integer.toString(p2s));
                    buttons.forEach(this::resetButton);
                    statusLBL.setText("Round " + counter);
                }
            }
        }

    }


    public void initPage(Player player1, Player player2) {

        this.player1 = player1;
        this.player2 = player2;

        player1LBL.setText(player1.getUsername() + " :");
        player2LBL.setText(player2.getUsername() + " :");

    }
}
