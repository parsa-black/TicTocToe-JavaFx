package com.company.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MultiPlayPageController implements Initializable {

    @FXML
    private ChoiceBox choiceBOX;

    @FXML
    private Button backBTN;

    static Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        choiceBOX.getItems().add("choice 1");
        choiceBOX.getItems().add("choice 2");
        choiceBOX.getItems().add("choice 3");


    }
}
