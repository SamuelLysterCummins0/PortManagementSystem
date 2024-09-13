package com.example.portsytemca1;


    import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



public class SceneManager {
        private static SceneManager instance;
        private Stage primaryStage;


        private SceneManager() {}

        public static SceneManager getInstance() {
            if (instance == null) {
                instance = new SceneManager();
            }
            return instance;
        }



        public void setPrimaryStage(Stage primaryStage) {
            this.primaryStage = primaryStage;
        }

        public void switchScene(String fxmlPath) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();


            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

