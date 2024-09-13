package Classes;

public class Container {

    private int containerNumber;

    private int containerSize;

    private boolean isOnShore;

    private ContainerShip currentShip;
    private Port currentPort;
    private LinkList<Pallet> pallets;


    public Container(int containerNumber, int containerSize) {
        this.containerNumber = containerNumber;
        this.containerSize = containerSize;
        this.pallets = new LinkList<>();
    }

    public int getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(int containerNumber) {
        if (ShippingSystem.isUniqueContainerNumber(containerNumber)) {
            this.containerNumber = containerNumber;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getContainerSize() {
        return containerSize;
    }

    public void setContainerSize(int containerSize) {
        this.containerSize = containerSize;
    }

    public void setOnShore(boolean onShore) {
        isOnShore = onShore;
    }

    public ContainerShip getCurrentShip() {
        return currentShip;
    }
    public void setCurrentShip(ContainerShip currentShip) {
        this.currentShip = currentShip;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    public LinkList<Pallet> getPallets() {
        return pallets;
    }

    public void setPallets(LinkList<Pallet> pallets) {
        this.pallets = pallets;
    }

    public void addPallet(Pallet pallet){
        pallets.add(pallet);
        pallet.setCurrentContainer(this);
    }



public String toString(){
        return " Container Num: "+containerNumber+ " Container Size: " +containerSize;
}
}
