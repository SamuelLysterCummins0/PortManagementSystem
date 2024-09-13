package com.example.portsytemca1;

import Classes.Container;
import Classes.ContainerShip;
import Classes.Port;
import Classes.ShippingSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PortControlsController extends GUIController {

    @FXML
    private Stage stage;

    private Scene scene;
    private Parent root;
    @FXML
    private ListView<ContainerShip> dockedShipsListView;
    @FXML
    private ListView<Port> portListView;
    @FXML
    private ListView<Container>shipContainersListView;
    @FXML
    private ListView<Container>portContainersListView;

    @FXML
    private ListView<ContainerShip>atSeaShipsListView;
    @FXML
    private Button myLoadContainerButton;

    @FXML
    private Button myUnloadContainerButton;

    @FXML
    private Button launchShipButton;
    @FXML
    private Button dockShipButton;
    private SceneManager sceneManager = SceneManager.getInstance();


    private ShippingSystem system;
    public PortControlsController() {
        this.system = ShippingSystem.getInstance();
    }
    public void initialize() {
        super.initialize();
        refreshPortListView();
        refreshDockedShipsListView();
        refreshAtSeaShipsListView();

        portListView.setOnMouseClicked(this::onPortListViewClicked);
        dockedShipsListView.setOnMouseClicked(this::onDockedShipListViewClicked);
        atSeaShipsListView.setOnMouseClicked(this::onAtSeaShipListViewClicked);
    }
    @FXML
    public void loadContainerButton(ActionEvent event) {
        Port selectedPort = portListView.getSelectionModel().getSelectedItem();
        ContainerShip selectedShip = dockedShipsListView.getSelectionModel().getSelectedItem();
        Container selectedContainer = portContainersListView.getSelectionModel().getSelectedItem();

        if (selectedPort != null && selectedContainer != null && selectedShip != null) {
            system.loadContainersToShip(selectedContainer, selectedShip);

            portContainersListView.getItems().remove(selectedContainer);
            shipContainersListView.getItems().add(selectedContainer);
        }
    }

    @FXML
    public void unloadContainerButton(ActionEvent event) {
        Port selectedPort = portListView.getSelectionModel().getSelectedItem();
        Container selectedContainer = shipContainersListView.getSelectionModel().getSelectedItem();
        ContainerShip selectedShip = dockedShipsListView.getSelectionModel().getSelectedItem();

        if (selectedPort != null && selectedContainer != null && selectedShip != null) {
            system.unloadContainersFromShip(selectedContainer, selectedShip);

            refreshPortContainersListView(selectedPort);
            refreshShipContainersListView(selectedShip);
        }
    }

    @FXML
    private void launchShipButton() {
        ContainerShip selectedShip = dockedShipsListView.getSelectionModel().getSelectedItem();
        if (selectedShip != null) {
            system.launchShip(selectedShip);
            refreshDockedShipsListView();
            refreshAtSeaShipsListView();
        }
    }

    @FXML
    private void dockShipButton() {
        ContainerShip selectedShip = atSeaShipsListView.getSelectionModel().getSelectedItem();
        Port selectedPort = portListView.getSelectionModel().getSelectedItem();
        if (selectedShip != null && selectedPort != null) {
            system.dockShip(selectedShip, selectedPort);
            refreshDockedShipsListView();
            refreshAtSeaShipsListView();
        }
    }

    private void onPortListViewClicked(MouseEvent event) {
        Port selectedPort = portListView.getSelectionModel().getSelectedItem();
        if (selectedPort != null) {
            this.port = selectedPort;
            refreshDockedShipsListView();
            refreshPortContainersListView(selectedPort);
            shipContainersListView.getItems().clear();
        }
    }
    private void onDockedShipListViewClicked(MouseEvent event) {
        ContainerShip selectedShip = dockedShipsListView.getSelectionModel().getSelectedItem();
        if (selectedShip != null) {
            refreshShipContainersListView(selectedShip);
        }
    }
        private void onAtSeaShipListViewClicked(MouseEvent event) {
            ContainerShip selectedShip = atSeaShipsListView.getSelectionModel().getSelectedItem();
            if (selectedShip != null) {
                refreshShipContainersListView(selectedShip);
            }
        }

    private void refreshPortListView() {
        portListView.getItems().clear();
        for (Port port : system.getAllPorts()) {
            portListView.getItems().add(port);
        }
    }

    private void refreshDockedShipsListView() {
        dockedShipsListView.getItems().clear();
        if (port != null) {
            for (ContainerShip ship : port.getDockedShips()) {
                dockedShipsListView.getItems().add(ship);
            }
        }
    }

    private void refreshShipContainersListView(ContainerShip ship) {
        shipContainersListView.getItems().clear();
        for (Container container : ship.getContainers()) {
            shipContainersListView.getItems().add(container);
        }
    }
    private void refreshPortContainersListView(Port selectedPort) {
        portContainersListView.getItems().clear();
        for (Container container : selectedPort.getStoredContainers()) {
            portContainersListView.getItems().add(container);
        }
    }

    private void refreshAtSeaShipsListView() {
        atSeaShipsListView.getItems().clear();
        for(ContainerShip ship: system.getShipsAtSea()){
            atSeaShipsListView.getItems().add(ship);
        }
    }
    public void switchToPort(ActionEvent event) throws IOException {
        SceneManager.getInstance().switchScene("Port.fxml");
    }

    public void switchToView(ActionEvent event) throws IOException {
        sceneManager.switchScene("View.fxml");
    }

    public void switchToSearch(ActionEvent event) throws IOException {
        sceneManager.switchScene("Search.fxml");
    }

    public void switchToSave(ActionEvent event) throws IOException {
        sceneManager.switchScene("Save.fxml");
    }
}
