package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button("ok");
        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(button);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
