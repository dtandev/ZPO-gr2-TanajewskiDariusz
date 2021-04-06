interface finalPrice {
    double BASE_PRICE = 15.00;
    double TOMATO_SAUCE = 5.00;
    double MOZZARELLA_PRICE = 2.50;
    double HAM_PRICE = 2.50;
    double getPrice();
}

class Pizza implements finalPrice {

    public double getPrice() {
        return BASE_PRICE+TOMATO_SAUCE;
    }

    public String showDescription() {
        return "Pizza";
    }
}

class PizzaWithMozzarella extends Pizza implements finalPrice {
    private final Pizza basePizza;
    public PizzaWithMozzarella(Pizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public double getPrice() {
        return basePizza.getPrice()+MOZZARELLA_PRICE;
    }

}

class PizzaWithHam extends Pizza implements finalPrice {
    private final Pizza basePizza;
    public PizzaWithHam(Pizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public double getPrice() {
        return basePizza.getPrice()+HAM_PRICE;
    }
}

class PizzaWithoutTomatoSauce extends Pizza implements finalPrice {
    private final Pizza basePizza;
    public PizzaWithoutTomatoSauce(Pizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public double getPrice() {
        return basePizza.getPrice()-basePizza.TOMATO_SAUCE;
    }
}


public class Pizzeria {

    public static void main(String[] args) {
        Pizza withHamAndMozarella = new PizzaWithHam(new PizzaWithMozzarella(new Pizza()));
        System.out.print("Cena pizzy z Szynką i Mozarellą: ");
        System.out.println(withHamAndMozarella.getPrice());

        Pizza withHamAndMozarellaWithoutTS = new PizzaWithoutTomatoSauce(new PizzaWithHam(new PizzaWithMozzarella(new Pizza())));
        System.out.print("Cena pizzy z Szynką i Mozarellą, ale bez sosu pomidorowego: ");
        System.out.print(withHamAndMozarellaWithoutTS.getPrice());

    }
}
