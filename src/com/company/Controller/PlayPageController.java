package com.company.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayPageController implements Initializable {

    @FXML
    private Button backBTN;

    @FXML
    private Button helpBTN;

    static Stage stage = null;

    public static Stage helpStage = null; //public because HelpPageController need to Access

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backBTN.setOnAction(e -> {
            ((Stage) backBTN.getScene().getWindow()).close();
            try {
                if (stage == null) {
                    AnchorPane root = FXMLLoader.load(this.getClass().getResource("../View/MainPageView.fxml"));
                    stage = new Stage();
                    stage.setTitle("MainPage");
                    stage.setScene(new Scene(root));
                    stage.show();
                    stage = null;
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        helpBTN.setOnAction(e -> {
            try {
                if (helpStage == null) {
                    AnchorPane root = FXMLLoader.load(this.getClass().getResource("../View/HelpPageOneView.fxml"));
                    helpStage = new Stage();
                    helpStage.setTitle("HelpPage");
                    helpStage.setScene(new Scene(root));
                    helpStage.show();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


    }
}
