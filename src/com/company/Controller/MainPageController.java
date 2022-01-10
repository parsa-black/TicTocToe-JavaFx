package com.company.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private Button exitBTN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        exitBTN.setOnAction(e -> ( (Stage) exitBTN.getScene().getWindow() ).close());

    }
}
