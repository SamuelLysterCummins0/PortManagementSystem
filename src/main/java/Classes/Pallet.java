package Classes;

public class Pallet {

    private String goodsDescription;

    private int numberOfGoods;

    private int unitValue;

    private int totalWeight;

    private int totalSize;
    private Container currentContainer;

    private Port currentPort;



    public Pallet(String goodsDescription, int numberOfGoods, int unitValue, int totalWeight, int totalSize) {
        this.goodsDescription = goodsDescription;
        this.numberOfGoods = numberOfGoods;
        this.unitValue = unitValue;
        this.totalWeight = numberOfGoods;
        this.totalSize = totalSize;

    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public int getNumberOfGoods() {
        return numberOfGoods;
    }

    public void setNumberOfGoods(int numberOfGoods) {
        this.numberOfGoods = numberOfGoods;
    }

    public int getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(int unitValue) {
        this.unitValue = unitValue;
    }
//
    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public boolean addPallet(){
        return true;
    }

    public Container getCurrentContainer() {
        return currentContainer;
    }

    public void setCurrentContainer(Container currentContainer) {
        this.currentContainer = currentContainer;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    public String toString(){
        return " Descr: " + goodsDescription + " Quantity: "+ numberOfGoods+ " unit value: "+ unitValue+ " total weight: "+ totalWeight + " total size: "+totalSize;
    }
}
