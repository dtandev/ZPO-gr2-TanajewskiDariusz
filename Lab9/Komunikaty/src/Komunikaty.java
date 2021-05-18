import java.util.*;

interface Obserwator {
    void zaktualizuj(String nazwa);
}

interface Podmiot {
    void dodaj(Obserwator obserwator);

    void usun(Obserwator obserwator);

    void poinformuj(String komunikat);
};

class Bank implements Podmiot {

    private ArrayList obserwatorzy;

    Bank() {
        obserwatorzy = new ArrayList();
    }

    @Override
    public void dodaj(Obserwator obserwator) {
        obserwatorzy.add(obserwator);
        System.out.println("Liczba podmiotów obserwujacych: " + obserwatorzy.size());
    }

    @Override
    public void usun(Obserwator obserwator) {
        obserwatorzy.remove(obserwator);
        System.out.println("Liczba podmiotów obserwujacych: " + obserwatorzy.size());
    }

    @Override
    public void poinformuj(String komunikat) {
        for (int i = 0; i < obserwatorzy.size(); i++) {
            Obserwator obserwator = (Obserwator) obserwatorzy.get(i);
            obserwator.zaktualizuj(komunikat);
        }
    }
}

class Klient implements Obserwator {

    private Bank bank;
    private ArrayList komunikaty = new ArrayList();


    public Klient (Bank bank) {
    }

    @Override
    public void zaktualizuj(String komunikat) {
        komunikaty.add(komunikat);
    }

    public ArrayList getKomunikaty() {
        return komunikaty;
    }
}

public class Komunikaty {
    public static void main(String[] args) {
        Bank pkobp = new Bank();
        Klient janKowalski = new Klient(pkobp);
        Klient adamNowak = new Klient(pkobp);

        // Jesli klient wyrazi zgode, nalezy go dodac do listy obserwatorow
        pkobp.dodaj(janKowalski);
        pkobp.poinformuj("1 maja bank nieczynny");
        System.out.println("Lista komunikatów Jana Kowalskiego: " + janKowalski.getKomunikaty());
        // dodanie nowego klienta do listy mailingowej
        pkobp.dodaj(adamNowak);
        // dodanie nowego komunikatu
        pkobp.poinformuj("3 maja bank nieczynny");
        System.out.println("#### Aktualizacja komunikatów #####");
        System.out.println("Lista komunikatów Jana Kowalskiego: " + janKowalski.getKomunikaty());
        System.out.println("Lista komunikatów Adama Nowaka: " + adamNowak.getKomunikaty());
        // Adam Nowak ma 1 komunikat, bo dołączył później.
        // Jan Kowalski ma 2 komunikaty
    }
}
