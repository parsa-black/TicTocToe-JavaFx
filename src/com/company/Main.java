package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/com/company/View/MainPageView.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/company/View/StyleSheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
