
abstract class Pizza {
    void prepareRecipe() {
        przygotujCiasto();
        dodajSosPomidorowy();
        dodajDodatki();
        piecz();
    }
    abstract void przygotujCiasto();
    abstract void dodajDodatki();

    final void dodajSosPomidorowy() {
        System.out.println("Dodaj sos pomidorowy");
    }

    final void piecz() {
        System.out.println("Piecz przez około 15 minut");
    }
}

class PizzaMargherita extends Pizza {

    public void przygotujCiasto() {
        System.out.println("Przygotuj cienkie ciasto");
    }

    public void dodajDodatki() {
        dodajSerMozzarella();
        dodajBazylieiOliwe();
    }

    public void dodajSerMozzarella() {
        System.out.println("Dodaj ser mozzarella");
    }

    public void dodajBazylieiOliwe() {
        System.out.println("Dodaj bazylię oraz odrobinę oliwy");
    }
}


class PizzaSycylijska extends Pizza {

    public void przygotujCiasto() {
        System.out.println("Przygotuj grube ciasto");
    }

    void dodajDodatki() {
        dodajOliwkiiKapary();
        dodajPrzyprawy();
    }

    public void dodajOliwkiiKapary() {
        System.out.println("Dodaj oliwki i kapary");
    }

    public void dodajPrzyprawy() {
        System.out.println("Dodaj przyprawy");
    }
}


public class Pizzeria {
    public static void main(String[] args) {

        PizzaMargherita pizzaMargherita= new PizzaMargherita();
        PizzaSycylijska pizzaSycylijska = new PizzaSycylijska();

        System.out.println("\nMaking pizza margherita...");
        pizzaMargherita.prepareRecipe();

        System.out.println("\nMaking pizza sycylisjka...");
        pizzaSycylijska.prepareRecipe();
    }


}