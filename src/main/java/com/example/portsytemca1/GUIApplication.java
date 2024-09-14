package com.example.portsytemca1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;


import java.io.IOException;

public class GUIApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneManager.getInstance().setPrimaryStage(primaryStage);
        SceneManager.getInstance().switchScene("Port.fxml");
    }
//
    public static void main(String[] args) {
        launch(args);
    }
}