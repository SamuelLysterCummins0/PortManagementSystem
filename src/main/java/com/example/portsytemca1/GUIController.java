package com.example.portsytemca1;

import Classes.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;




public class GUIController {

    @FXML
    private Stage stage;

    private Scene scene;
    private Parent root;
    @FXML
    private TextField portNameField;
    @FXML
    private TextField portCodeField;
    @FXML
    private TextField portCountryField;
    @FXML
    private TextField shipNameField;
    @FXML
    private TextField shipCodeField;
    @FXML
    private TextField flagStateField;
    @FXML
    private TextField photographField;
    @FXML
    private TextField containerNumberField;
    @FXML
    private CheckBox checkBox10;
    @FXML
    private CheckBox checkBox20;
    @FXML
    private CheckBox checkBox40;

    @FXML
    private TextField descrField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField totalSizeField;
    @FXML
    private TextField unitValueField;
    @FXML
    private TextField totalWeightField;

    @FXML
    private Button addContainerButton;

    @FXML
    private ListView<Port> portListView;
    @FXML
    private ListView<ContainerShip> dockedShipsListView;
    @FXML
    private ListView<Container>portContainersListView;
    @FXML
    private ListView<Container>shipContainersListView;
    @FXML


    private ListView<Pallet> palletListView;

    @FXML
    private Button myPortButton;
    private SceneManager sceneManager = SceneManager.getInstance();

    public Port port;
    private ShippingSystem system;

    public GUIController(){
        system = ShippingSystem.getInstance();
    }


    public void initialize() {
        refreshPortListView();
        if (!portListView.getItems().isEmpty()) {
            port = portListView.getSelectionModel().getSelectedItem();
            refreshDockedShipsListView();
        }
        portListView.setOnMouseClicked(this::onPortListViewClicked);
        dockedShipsListView.setOnMouseClicked(this::onShipListViewClicked);
        shipContainersListView.setOnMouseClicked(this::onShipContainerSelected);
        portContainersListView.setOnMouseClicked(this::onPortContainerSelected);
    }


    private void onPortListViewClicked(MouseEvent event) {
        Port selectedPort = portListView.getSelectionModel().getSelectedItem();
        if (selectedPort != null) {
            this.port = selectedPort;
            refreshDockedShipsListView();
            refreshPortContainersListView(selectedPort);
            dockedShipsListView.getSelectionModel().clearSelection();
            shipContainersListView.getItems().clear();

        }
    }
    private void onShipListViewClicked(MouseEvent event) {
        ContainerShip selectedShip = dockedShipsListView.getSelectionModel().getSelectedItem();
        if (selectedShip != null) {
            refreshShipContainersListView(selectedShip);
            portListView.getSelectionModel().clearSelection();

        }
    }
    private void onShipContainerSelected(MouseEvent event) {
        Container selectedContainer = shipContainersListView.getSelectionModel().getSelectedItem();
        if (selectedContainer != null) {
            portContainersListView.getSelectionModel().clearSelection();
            refreshPalletListView(selectedContainer);
        } else {
            palletListView.getItems().clear();
        }
    }


    private void onPortContainerSelected(MouseEvent event) {
        Container selectedContainer = portContainersListView.getSelectionModel().getSelectedItem();
        if (selectedContainer != null) {
            shipContainersListView.getSelectionModel().clearSelection();
            refreshPalletListView(selectedContainer);
        } else {
            palletListView.getItems().clear();
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

    private void refreshPalletListView(Container container) {
        if (palletListView != null) {
            palletListView.getItems().clear();
            for (Pallet pallet : container.getPallets()) {
                palletListView.getItems().add(pallet);
            }
        }
    }

    public void addPortButton(ActionEvent event) throws IOException {
       try{
        String portName = portNameField.getText();
        int portCode = Integer.parseInt(portCodeField.getText());
        String portCountry = portCountryField.getText();

        Port newPort = new Port(portName, portCode, portCountry);
        newPort.setPortCode(portCode);
        system.addPort(newPort);
        refreshPortListView();
       } catch (IllegalArgumentException e) {

       }

    }

    public void addContainerShipButton(ActionEvent event) throws IOException {
        try {
            Port selectedPort = portListView.getSelectionModel().getSelectedItem();
            if (selectedPort != null) {
                String shipName = shipNameField.getText();
                int shipCode = Integer.parseInt(shipCodeField.getText());
                String flagState = flagStateField.getText();
                String photograph = photographField.getText();

                ContainerShip newShip = new ContainerShip(shipName, shipCode, flagState, photograph);

                newShip.setShipCode(shipCode);
                selectedPort.addContainerShip(newShip);
                refreshDockedShipsListView();

            }
        }catch (IllegalArgumentException e){

        }
    }

    public void addContainerButton(ActionEvent event)throws IOException {
try {
    Port selectedPort = portListView.getSelectionModel().getSelectedItem();
    ContainerShip selectedShip = dockedShipsListView.getSelectionModel().getSelectedItem();

    if (selectedPort != null) {

        int containerNumber = Integer.parseInt(containerNumberField.getText());
        int containerSize = getSelectedContainerSize();

        Container newContainer = new Container(containerNumber, containerSize);

        newContainer.setContainerNumber(containerNumber);
        selectedPort.addContainerToPort(newContainer);
        refreshPortContainersListView(selectedPort);
    }

        if (selectedShip != null) {

            int containerNumber = Integer.parseInt(containerNumberField.getText());;
            int containerSize = getSelectedContainerSize();

            Container newContainer = new Container(containerNumber, containerSize);

            newContainer.setContainerNumber(containerNumber);
            selectedShip.addContainerToShip(newContainer);
            refreshShipContainersListView(selectedShip);
        }
}catch (IllegalArgumentException e){

}
    }
    //
public void addPalletButton(){
    Container selectedShip = shipContainersListView.getSelectionModel().getSelectedItem();
Container selectedContainer = portContainersListView.getSelectionModel().getSelectedItem();

    if (selectedShip != null) {
        String goodsDescription = descrField.getText();
        int numberOfGoods = Integer.parseInt(quantityField.getText());
        int unitValue = Integer.parseInt(unitValueField.getText());
        int totalWeight = Integer.parseInt(totalWeightField.getText());
        int totalSize = Integer.parseInt(totalSizeField.getText());

        Pallet newPallet = new Pallet(goodsDescription, numberOfGoods, unitValue, totalWeight, totalSize);
        selectedShip.addPallet(newPallet);
        refreshPalletListView(selectedShip);
    }
    if (selectedContainer != null) {
        String goodsDescription = descrField.getText();
        int numberOfGoods = Integer.parseInt(quantityField.getText());
        int unitValue = Integer.parseInt(unitValueField.getText());
        int totalWeight = Integer.parseInt(totalWeightField.getText());
        int totalSize = Integer.parseInt(totalSizeField.getText());

        Pallet newPallet = new Pallet(goodsDescription, numberOfGoods, unitValue, totalWeight, totalSize);
        selectedContainer.addPallet(newPallet);
        refreshPalletListView(selectedContainer);
    }
    }


    private int getSelectedContainerSize(){
        if(checkBox10.isSelected()){
            return 10;
        } else if(checkBox20.isSelected()){
            return 20;
        }else if(checkBox40.isSelected()){
            return 40;
        }
return -1;
    }
    @FXML
    private void handleCheckBoxAction(ActionEvent event) {
        CheckBox selectedCheckBox = (CheckBox) event.getSource();

        if (selectedCheckBox.equals(checkBox10)) {
            if (selectedCheckBox.isSelected()) {
                checkBox20.setSelected(false);
                checkBox40.setSelected(false);
            }
        } else if (selectedCheckBox.equals(checkBox20)) {
            if (selectedCheckBox.isSelected()) {
                checkBox10.setSelected(false);
                checkBox40.setSelected(false);
            }
        } else if (selectedCheckBox.equals(checkBox40)) {
            if (selectedCheckBox.isSelected()) {
                checkBox10.setSelected(false);
                checkBox20.setSelected(false);
            }
        }
    }



    public void switchToPortControls(ActionEvent event) throws IOException {
        SceneManager.getInstance().switchScene("PortControls.fxml");
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
