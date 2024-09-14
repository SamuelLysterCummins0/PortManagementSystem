package com.example.portsytemca1;

import Classes.ShippingSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SaveController {
    @FXML
    private Stage stage;

    private Scene scene;
    private Parent root;

    private ShippingSystem system;

    public SaveController() {
        this.system = ShippingSystem.getInstance();
    }

//
    @FXML
    private void saveButton(ActionEvent event) {
        try {
            system.save("src/main/resources/system_data.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadButton(ActionEvent event) {
        try {
            ShippingSystem loadedSystem = ShippingSystem.load("src/main/resources/system_data.xml");
            ShippingSystem.setInstance(loadedSystem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void resetSystem(ActionEvent event) {
        system.resetSystemData();
        try {
            system.save("src/main/resources/system_data.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void switchToPort(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Port.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPortControls(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PortControls.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Search.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
