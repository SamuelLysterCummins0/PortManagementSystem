package com.example.portsytemca1;

import Classes.ShippingSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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




    @FXML
    private void switchToPort() throws IOException {
        SceneManager.getInstance().switchScene("Port.fxml");
    }

    @FXML
    private void switchToPortControls() throws IOException {
        SceneManager.getInstance().switchScene("PortControls.fxml");
    }

    @FXML
    private void switchToSearch() throws IOException {
        SceneManager.getInstance().switchScene("Search.fxml");
    }

    public void switchToView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleButtonHover(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: white; -fx-text-fill: #14242B; -fx-border-color: white; -fx-border-width: 2px;");
    }

    @FXML
    public void handleButtonExit(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px;");
    }

    @FXML
    public void handleTextFieldFocus(MouseEvent event) {
        TextField textField = (TextField) event.getSource();
        textField.setStyle("-fx-background-color: white; -fx-text-fill: #14242B; -fx-border-color: white; -fx-border-width: 1px;");
    }

    @FXML
    public void handleTextFieldUnfocus(MouseEvent event) {
        TextField textField = (TextField) event.getSource();
        textField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 1px;");
    }
}
