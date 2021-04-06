class Prezent {
    static double kosztPrezentu = 0.00;
    static double kosztyWysylki = 13.00;

    public double getPrice() {
        return kosztPrezentu;
    }

    public double getFinalPrice() {
        if (getPrice()<0) {
            return kosztyWysylki;
        } else {
            return getPrice()+kosztyWysylki;
        }
    }

    public String showDescription() {
        return "Dostales prezent";
    }
}

class Smycz extends Prezent {
    private static double cenaSmyczy = 1.00;
    private final Prezent prezent;

    public Smycz(Prezent prezent) {
        this.prezent = prezent;
    }

    @Override
    public double getPrice() {
        System.out.println(showDescription());
        return prezent.getPrice()+cenaSmyczy;
    }

    @Override
    public String showDescription() {
        return "Dostales smycz";
    }
}

class Maskotka extends Prezent {
    private static double cenaMaskotki = 0.00;
    private final Prezent prezent;

    public Maskotka(Prezent prezent) {
        this.prezent = prezent;
    }

    @Override
    public double getPrice() {
        System.out.println(showDescription());
        return prezent.getPrice()+cenaMaskotki;
    }

    @Override
    public String showDescription() {
        return "Dostales maskotke";
    }
}

class Rabat extends Prezent {
    private static double rabat = -10.00;
    private final Prezent prezent;

    public Rabat(Prezent prezent) {
        this.prezent = prezent;
    }

    @Override
    public double getPrice() {
        System.out.println(showDescription());
        return prezent.getPrice()+rabat;
    }

    @Override
    public String showDescription() {
        return "Dostales rabat";
    }

}

public class Sklep {

    public static void main(String[] args) {

        Prezent mis = new Maskotka(new Prezent());
        Prezent smyczFirmowa = new Smycz(mis);
        Prezent kuponRabatowy = new Rabat(smyczFirmowa);
        Prezent smyczLotosu = new Smycz(kuponRabatowy);

        System.out.format("Koszt zamówienia łącznie z kosztami wysyłki: %.2f", smyczLotosu.getFinalPrice());
        // Jeśli suma wszystkich wydatków na prezenty jest mniejsza niż 0, to kupujący pokrywa jedynie koszty wysyłki
        // Konieczne jest wywołanie odrębnej metody getFinalPrice, ponieważ kupon rabatowy zerował
        // wartość zamówienia, ale po dodaniu kolejnego produktu metoda getPrice zwracała  wartość tego produktu,
        // choć powinien on być objęty przyznanym rabatem.

    }
}

