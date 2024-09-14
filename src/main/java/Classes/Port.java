package Classes;

public class Port {

    private String portName;

    private int portCode;

    private String portCountry;

    private LinkList<ContainerShip>dockedShips;


    private LinkList<Container>storedContainers;

    private LinkList<Pallet> pallets;
     private ShippingSystem system;


    public Port(String portName, int portCode, String portCountry) {
        this.portName = portName;
        this.portCode = portCode;
        this.portCountry = portCountry;
        this.storedContainers = new LinkList<>();
        this.dockedShips = new LinkList<>();
        this.pallets = new LinkList<>();
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public int getPortCode() {
        return portCode;
    }

    public void setPortCode(int portCode) {
        if(ShippingSystem.isUniquePortCode(portCode)){
            this.portCode = portCode;
        }else{
            throw new IllegalArgumentException();
        }
    }
//
    public String getPortCountry() {
        return portCountry;
    }

    public void setPortCountry(String portCountry) {
        this.portCountry = portCountry;
    }

    public LinkList<ContainerShip> getDockedShips() {
        return dockedShips;
    }

    public void setDockedShips(LinkList<ContainerShip> dockedShips) {
        this.dockedShips = dockedShips;
    }

    public LinkList<Container> getStoredContainers() {
        return storedContainers;
    }

    public void setStoredContainers(LinkList<Container> storedContainers) {
        this.storedContainers = storedContainers;
    }

    public LinkList<Pallet> getPallets() {
        return pallets;
    }

    public void setPallets(LinkList<Pallet> pallets) {
        this.pallets = pallets;
    }

    public void addContainerToPort(Container container){
        storedContainers.add(container);
        container.setCurrentPort(this);
    }

    public void addContainerShip(ContainerShip ship){
        dockedShips.add(ship);
        ship.setCurrentPort(this);
    }



    public String toString(){
        return "Port Name:" + portName + " Port Code:" + portCode+ " Country:"+ portCountry;
    }
}

