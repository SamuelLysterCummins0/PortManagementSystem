package com.example.portsytemca1;

import Classes.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchController {
    @FXML
    private TextField searchField;
    
    @FXML
    private Button searchButton;
    
    @FXML
    private ListView<String> resultsListView;

    private ShippingSystem system;
    private SceneManager sceneManager;

    public SearchController() {
        this.system = ShippingSystem.getInstance();
        this.sceneManager = SceneManager.getInstance();
    }

    @FXML
    public void initialize() {
        searchButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleButtonHover(event);
            }
        });
        searchButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleButtonExit(event);
            }
        });
        searchField.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleTextFieldFocus(event);
            }
        });
        searchField.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleTextFieldUnfocus(event);
            }
        });
    }

    @FXML
    public void searchGoods(ActionEvent event) {
        String searchTerm = searchField.getText().trim().toLowerCase();
        if (!searchTerm.isEmpty()) {
            resultsListView.getItems().clear();
            searchInSystem(searchTerm);
        }
    }

    private void searchInSystem(String searchTerm) {
        List<String> results = new ArrayList<>();
        for (Port port : system.getAllPorts()) {
            
            for (Container container : port.getStoredContainers()) {
                searchInContainer(container, searchTerm, "Port: " + port.getPortName(), results);
            }
            
            
            for (ContainerShip ship : port.getDockedShips()) {
                for (Container container : ship.getContainers()) {
                    searchInContainer(container, searchTerm, 
                        "Ship: " + ship.getShipName() + " (Docked at " + port.getPortName() + ")", 
                        results);
                }
            }
        }
        
        for (ContainerShip ship : system.getShipsAtSea()) {
            for (Container container : ship.getContainers()) {
                searchInContainer(container, searchTerm, 
                    "Ship: " + ship.getShipName() + " (At Sea)", 
                    results);
            }
        }

        if (results.isEmpty()) {
            resultsListView.getItems().add("No goods found matching: " + searchTerm);
        } else {
            resultsListView.getItems().addAll(results);
        }
    }

    private void searchInContainer(Container container, String searchTerm, String location, List<String> results) {
        for (Pallet pallet : container.getPallets()) {
            if (pallet.getGoodsDescription().toLowerCase().contains(searchTerm)) {
                results.add(String.format("Location: %s, Container: %d%n" +
                        "Goods: %s%n" +
                        "Quantity: %d, Unit Value: %d, Total Weight: %d, Total Size: %d",
                    location, container.getContainerNumber(),
                    pallet.getGoodsDescription(),
                    pallet.getNumberOfGoods(), pallet.getUnitValue(),
                    pallet.getTotalWeight(), pallet.getTotalSize()));
            }
        }
    }

    @FXML
    public void switchToPort(ActionEvent event) throws IOException {
        sceneManager.switchScene("Port.fxml");
    }

    @FXML
    public void switchToPortControls(ActionEvent event) throws IOException {
        sceneManager.switchScene("PortControls.fxml");
    }

    @FXML
    public void switchToSave(ActionEvent event) throws IOException {
        sceneManager.switchScene("Save.fxml");
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
