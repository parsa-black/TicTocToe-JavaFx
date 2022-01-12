package com.company.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private Button exitBTN;

    @FXML
    private Button playBTN;

    @FXML
    private Hyperlink creatorBTN;

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
                    stage.setTitle("TIK TOK TOE");
                    stage.setScene(new Scene(root));
                    stage.show();
                    stage = null;
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        creatorBTN.setOnAction(e -> {
            try {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/parsa-black/TikTokToe-Game"));
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }
}
