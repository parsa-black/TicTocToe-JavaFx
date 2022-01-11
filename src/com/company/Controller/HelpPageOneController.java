package com.company.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.company.Controller.PlayPageController.helpStage;

public class HelpPageOneController implements Initializable {

    @FXML
    private Button exitBTN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        exitBTN.setOnAction(event -> {
            ( (Stage) exitBTN.getScene().getWindow() ).close();
            helpStage = null;
        });

    }
}
