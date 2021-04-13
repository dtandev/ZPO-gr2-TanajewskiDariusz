import java.util.*;

interface Obserwator {
    void zaktualizuj(String nazwa, double cena);
}

interface Podmiot {
    void dodaj(Obserwator obserwator);

    void usun(Obserwator obserwator);

    void poinformuj(String nazwa, double cena);
};

class Gielda implements Podmiot {

    private ArrayList obserwatorzy;

    HashMap spolki = new HashMap();
    Date data;


    Gielda() {
        obserwatorzy = new ArrayList();
    }
    public void dodajSpolke(String nazwa, double cena) {
        this.spolki.put(nazwa, cena);
        System.out.println("Dodano spolke "+nazwa);
    }

    public void usunSpolkę(String nazwa)
    {
        this.spolki.remove(nazwa);
        System.out.println("Usunieto spolke "+nazwa);
    }

    public void zmienCene(String nazwa, double nowaCena) {
        this.spolki.replace(nazwa, nowaCena);
        System.out.println("Zmieniono cene spolki "+nazwa+". Nowa cena to "+ nowaCena);
        this.poinformuj(nazwa, nowaCena);
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
    public void poinformuj(String nazwa, double cena) {
        for (int i = 0; i < obserwatorzy.size(); i++) {
            Obserwator obserwator = (Obserwator) obserwatorzy.get(i);
            obserwator.zaktualizuj(nazwa, cena);
        }
    }
}

class MinisterFinansow implements Obserwator {

    private Gielda gielda;
    List<String> listaSpolek;
    HashMap listaCen;

    public MinisterFinansow(Gielda gielda) {
        this.gielda = gielda;
        gielda.dodaj(this);
        this.listaCen = new HashMap();

        List<String> listaSpolek = new ArrayList<>(gielda.spolki.keySet());

        for (int i=0; i<listaSpolek.size(); i++) {
            List cenySpolki = new ArrayList();
            cenySpolki.add(gielda.spolki.get(listaSpolek.get(i)));
            cenySpolki.add(1.0);
            listaCen.put(listaSpolek.get(i), cenySpolki);
        }
    }

    @Override
    public void zaktualizuj(String nazwa, double cena) {
        List priceInfo = (List) listaCen.get(nazwa);
        double counts = (double) priceInfo.get(1);
        double midPrice = (double) priceInfo.get(0);
        priceInfo.set(0, (midPrice * counts + cena) / (counts + 1));
        priceInfo.set(1, counts + 1);
        this.listaCen.remove(nazwa);
        this.listaCen.put(nazwa, priceInfo);
        System.out.println("MinisterFinansow: Srednia cena spolki "+nazwa+" po aktualizacji to "+priceInfo.get(0));
    }
}

class InwestorIndywidualny implements Obserwator {
    HashMap listaCen;
    private Gielda gielda;

    public InwestorIndywidualny (Gielda gielda) {
        this.gielda = gielda;
        gielda.dodaj(this);
        this.listaCen = (HashMap) gielda.spolki.clone();
    }

    @Override
    public void zaktualizuj(String nazwa, double cena) {
        double cenaAkcji = (double) listaCen.get(nazwa);
        if (cena/cenaAkcji > 1.1) {
            System.out.println("Inwestor Indywidualny: Sprzedaj akcje spolki "+nazwa);
            listaCen.put(nazwa, cena);
        } else if (cena/cenaAkcji < 0.95) {
            System.out.println("Inwestor Indywidualny: Kup akcje spólki "+nazwa);
            listaCen.put(nazwa, cena);
        } else {
            listaCen.put(nazwa, cena);
        }
    }
    // InwestorIndywidualny - kupuje jeśli cena spadnie o więcej niż 5% i sprzedaje, jeśli cena wzrośnie o więcej niż 10%
}

class Bank implements Obserwator {

    private Gielda gielda;

    public Bank (Gielda gielda) {
        this.gielda = gielda;
        gielda.dodaj(this);
    }

    @Override
    public void zaktualizuj(String nazwa, double cena) {
        if (cena > 10000 ) {
            System.out.println("Bank: Kup akcje spolki "+nazwa);
        }
    }
}


public class Gpw {
    public static void main(String[] args) {
        Gielda gpw = new Gielda();
        gpw.dodajSpolke("Orlen", 100);
        gpw.dodajSpolke("Lotos", 55);
        gpw.dodajSpolke("SANPL", 208);

        MinisterFinansow ministerFinansow = new MinisterFinansow(gpw);
        InwestorIndywidualny inwestorIndywidualny = new InwestorIndywidualny(gpw);
        Bank pko = new Bank(gpw);
        // Inwestor powinien kupic
        gpw.zmienCene("Orlen", 94);
        gpw.zmienCene("Orlen", 98);
        // Inwestor powinien sprzedac
        gpw.zmienCene("Orlen", 118);
        // Bank powinien kupic
        gpw.zmienCene("Orlen", 11000);
    }
}
