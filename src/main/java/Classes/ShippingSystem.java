package Classes;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
//
import java.io.*;

public class ShippingSystem   {
    private static ShippingSystem instance;

private LinkList<Port> ports;
    private LinkList<ContainerShip> shipsAtSea;
public ShippingSystem(){

    ports = new LinkList<>();
    shipsAtSea = new LinkList<>();
}
    public static ShippingSystem getInstance() {
        if (instance == null) {
            instance = new ShippingSystem();
        }
        return instance;
    }

    public static void setInstance(ShippingSystem system) {
        ShippingSystem.instance = system;
    }
public LinkList<Port> getAllPorts(){
        return ports;
    }

    public LinkList<ContainerShip> getShipsAtSea() {
        return shipsAtSea;
    }

    public void setShipsAtSea(LinkList<ContainerShip> shipsAtSea) {
        this.shipsAtSea = shipsAtSea;
    }

    public void addPort(Port port){
        ports.add(port);
    }


public void loadContainersToShip(Container container, ContainerShip ship){
    Port currentPort = container.getCurrentPort();

    if(ship.getCurrentPort() !=null){
        if(currentPort !=null){
            currentPort.getStoredContainers().remove(container);
            ship.addContainerToShip(container);
            container.setCurrentShip(ship);
        }
        }
    }

public void unloadContainersFromShip(Container container, ContainerShip ship) {
    Port currentPort = ship.getCurrentPort();

    if (currentPort != null) {
        if (ship.getContainers().contains(container)) {
            ship.getContainers().remove(container);
            currentPort.getStoredContainers().add(container);
            container.setCurrentShip(null);
            container.setCurrentPort(currentPort);

        }
    }
}

    public boolean launchShip(ContainerShip ship) {
        Port currentPort = ship.getCurrentPort();
        if (currentPort != null) {
            currentPort.getDockedShips().remove(ship);
            ship.setCurrentPort(null);
            shipsAtSea.add(ship);
        }
        return false;
    }

    public boolean dockShip(ContainerShip ship, Port port) {
        if (ship.getCurrentPort() == null) {
            port.getDockedShips().add(ship);
            ship.setCurrentPort(port);
            shipsAtSea.remove(ship);
        }
        return false;
    }

    public static boolean isUniquePortCode(int portCode) {
        for (Port port : instance.getAllPorts()) {
            if (port.getPortCode() == portCode) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUniqueShipCode(int shipCode) {
        for (Port port : instance.getAllPorts()) {
            for (ContainerShip ship : port.getDockedShips()) {
                if (ship.getShipCode() == shipCode) {
                    return false;
                }
            }
        }
        for (ContainerShip ship : instance.getShipsAtSea()) {
            if (ship.getShipCode() == shipCode) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUniqueContainerNumber(int containerNumber) {
        for (Port port : instance.getAllPorts()) {
            for (Container container : port.getStoredContainers()) {
                if (container.getContainerNumber() == containerNumber) {
                    return false;
                }
            }
            for (ContainerShip ship : port.getDockedShips()) {
                for (Container container : ship.getContainers()) {
                    if (container.getContainerNumber() == containerNumber) {
                        return false;
                    }
                }
            }
        }
        for (ContainerShip ship : instance.getShipsAtSea()) {
            for (Container container : ship.getContainers()) {
                if (container.getContainerNumber() == containerNumber) {
                    return false;
                }
            }
        }
        return true;
    }




    @SuppressWarnings("unchecked")
    public void save(String filePath) throws IOException {
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[]{Port.class, ContainerShip.class, Container.class, Pallet.class, ShippingSystem.class, LinkList.class, LinkNode.class, LinkIterator.class});

        try (ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(filePath))) {
            out.writeObject(this);
        }
    }

    public static ShippingSystem load(String filePath) throws IOException, ClassNotFoundException {
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[]{Port.class, ContainerShip.class, Container.class, Pallet.class, ShippingSystem.class});

        try (ObjectInputStream is = xstream.createObjectInputStream(new FileReader(filePath))) {
            return (ShippingSystem) is.readObject();
        }
    }

        public void resetSystemData() {
            ports = new LinkList<>();
            shipsAtSea = new LinkList<>();
        }

}
