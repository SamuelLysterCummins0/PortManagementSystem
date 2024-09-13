package Classes;

public class ContainerShip  {

private String shipName;

private int shipCode;

private String flagState;

private String photograph;

private Port currentPort;

private LinkList<Container> containers;

    public ContainerShip(String shipName, int shipCode, String flagState, String photograph) {
        this.shipName = shipName;
        this.shipCode = shipCode;
        this.flagState = flagState;
        this.photograph = photograph;
        this.containers = new LinkList<>();
    }

    public LinkList<Container> getContainers() {
        return containers;
    }

    public void setContainers(LinkList<Container> containers) {
        this.containers = containers;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getShipCode() {
        return shipCode;
    }

    public void setShipCode(int shipCode) {
        if (ShippingSystem.isUniqueShipCode(shipCode)) {
            this.shipCode = shipCode;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getFlagState() {
        return flagState;
    }

    public void setFlagState(String flagState) {
        this.flagState = flagState;
    }

    public String getPhotograph() {
        return photograph;
    }

    public void setPhotograph(String photograph) {
        this.photograph = photograph;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }


    public void addContainerToShip(Container container){
        containers.add(container);
        container.setCurrentShip(this);

    }
public String toString(){
        return " Ship Name: " + shipName + " Ship code: " + shipCode + " flagState: " + flagState + " photograph: " + photograph;
}
}
