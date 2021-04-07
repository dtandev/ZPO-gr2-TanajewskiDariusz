abstract class Stamp {
    void packing () {
        System.out.println("Stamp was packed");
    }
    void send () {
        System.out.println("Stamp was sent");
    }

    void complaining () {
        System.out.println("Something wrong");
    }
}

class ShipmentByPost extends Stamp {
    public void send() {
        System.out.println("Sending by post office");
    }
}

class ShipmentByCourier extends Stamp {
    public void send() {
        System.out.println("Sending by UPS");
    }
}

class PickupInPerson extends Stamp {
    public void send() {
        System.out.println("Pickup in person");
    }
}

class Shop {
    ShipmentFactory shipmentFactory;

    public Shop(ShipmentFactory shipmentFactory) {
        this.shipmentFactory = shipmentFactory;
    }

    public void orderStamp (String type) {
        Stamp stamp;
        stamp = shipmentFactory.shipmentType(type);
        stamp.packing();
        stamp.send();
    }
}

public class SklepZeZnaczkami {
    public static void main(String[] args) {
        ShipmentFactory factory = new ShipmentFactory();
        Shop shopInWarsaw = new Shop(factory);
        shopInWarsaw.orderStamp("post");
    }
}
