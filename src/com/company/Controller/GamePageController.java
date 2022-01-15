package com.company.Controller;

import com.company.Model.Player;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.company.Controller.PlayerDB.editUser;

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

    @FXML
    private Label winnerLBL;

    @FXML
    private Button backBTN;

    static Stage stage;

    private int p1s = 0; //Player1Score

    private int p2s = 0; //Player2Score

    float oneScore;

    float twoScore;


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
            winnerLBL.setText("");
            statusLBL.setText("Round 1");
            player1Score.setText("0");
            player2Score.setText("0");
            p1s = 0;
            p2s = 0;
            counter = 1;
            try {
                checkIfGameIsOver();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        backBTN.setOnAction(e -> {
            ((Stage) backBTN.getScene().getWindow()).close();

            try {
                if (stage == null) {
                    p1s = 0;
                    p2s = 0;
                    counter = 1;
                    AnchorPane root = FXMLLoader.load(this.getClass().getResource("../View/MultiPlayPageView.fxml"));
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


    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    private void stopGame() throws SQLException {
        editUser(player1);
        editUser(player2);
        reStartBTN.setDisable(false);
        buttons.forEach(this::setButton);
        if (p1s > p2s) {
             oneScore = player1.getScore();
            oneScore += 1.0;
             player1.setScore(oneScore);
            winnerLBL.setText(player1.getUsername() + " Is Won");
            editUser(player1);
        } else if (p2s > p1s) {
            twoScore = player2.getScore();
            twoScore += 1.0;
            player2.setScore(twoScore);
            winnerLBL.setText(player2.getUsername() + " Is Won");
            editUser(player2);
        } else if (p1s == p2s) {
            oneScore = player1.getScore();
            twoScore = player2.getScore();
            oneScore += 0.5;
            twoScore += 0.5;
            player1.setScore(oneScore);
            player1.setScore(twoScore);
            winnerLBL.setText("Is Draw");
            editUser(player1);
            editUser(player2);
        }

    }

    private void setButton(Button button) {
        button.setDisable(true);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            try {
                checkIfGameIsOver();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    public void checkIfGameIsOver() throws SQLException {
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
            if (line.equals("XXX")) {
                counter++;
                p1s++;
                player1Score.setText(Integer.toString(p1s));
                if (counter > 3) {
                    statusLBL.setText("Game IS Done");
                    stopGame();
                } else {
                    buttons.forEach(this::resetButton);
                    statusLBL.setText("Round " + counter);
                }
            } else if (line.equals("OOO")) {
                counter++;
                p2s++;
                player2Score.setText(Integer.toString(p2s));
                if (counter > 3) {
                    statusLBL.setText("Game IS Done");
                    stopGame();
                } else {
                    buttons.forEach(this::resetButton);
                    statusLBL.setText("Round " + counter);
                }
            } else if (button1.isDisable() && button2.isDisable() && button3.isDisable() && button4.isDisable() &&
                    button5.isDisable() && button6.isDisable() && button7.isDisable() && button8.isDisable() && button9.isDisable()) {
                counter++;
                if (counter > 3) {
                    statusLBL.setText("Game IS Done");
                    stopGame();
                } else {
                    buttons.forEach(this::resetButton);
                    statusLBL.setText("Round " + counter);
                }

            }

        }

    }


    public void initPage(Player player1, Player player2) {

        this.player1 = player1;
        this.player2 = player2;

        oneScore = player1.getScore();
        twoScore = player2.getScore();

        player1LBL.setText(player1.getUsername() + " :");
        player2LBL.setText(player2.getUsername() + " :");

    }
}
