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

    static Stage stage = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backBTN.setOnAction(e ->{
            ( (Stage) backBTN.getScene().getWindow() ).close();
            try {
                if (stage == null){
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

    }
}
