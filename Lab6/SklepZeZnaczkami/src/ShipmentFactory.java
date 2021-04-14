public class ShipmentFactory {

    public Stamp shipmentType(String shipmentType) {
        Stamp stamp = null;

        if (shipmentType.equals("post")) {
            stamp = new ShipmentByPost();
        } else if (shipmentType.equals("courier")) {
            stamp = new ShipmentByCourier();
        } else if (shipmentType.equals("pickup")) {
            stamp = new PickupInPerson();
        } else {
            System.out.println("Nie ma możliwości wysylki w taki sposób");
        }
        return stamp;
    }
}
