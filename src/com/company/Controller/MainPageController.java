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

public class MainPageController implements Initializable {

    @FXML
    private Button exitBTN;

    @FXML
    private Button playBTN;

    static Stage stage = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        exitBTN.setOnAction(event -> ( (Stage) exitBTN.getScene().getWindow() ).close() );

        playBTN.setOnAction(e -> {
            ( (Stage) exitBTN.getScene().getWindow() ).close();
            try{
                if ( stage == null ){
                    AnchorPane root = FXMLLoader.load(this.getClass().getResource("../View/PlayPageView.fxml"));
                    stage = new Stage();
                    stage.setTitle("play view");
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }
}
