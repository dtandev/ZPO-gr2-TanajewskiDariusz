interface Pracowac {
    void pracuj();
}

interface SpedzanieWolnegoCzasu {
    void spedzajWolnyCzas();
}

interface Dojezdzac {
    void dojezdzaj();
}

class LiteraturaPopularnoNaukowa implements SpedzanieWolnegoCzasu {
    @Override
    public void spedzajWolnyCzas() {
        System.out.println("Czyta ksiazke");
    }
}

class Silowania implements SpedzanieWolnegoCzasu {
    @Override
    public void spedzajWolnyCzas() {
        System.out.println("Selfie ze sztangami");
    }
}

class Samochod implements Dojezdzac {
    @Override
    public void dojezdzaj() {
        System.out.println("Brum brum");
    }
}

class Rower implements Dojezdzac {
    @Override
    public void dojezdzaj() {
        System.out.println("Szu Szu");
    }
}

class NaprawaSamochodow implements Pracowac {
    @Override
    public void pracuj() {
        System.out.println("Naprawiam Samochody");
    }
}

class Leczenie implements Pracowac {
    @Override
    public void pracuj() {
        System.out.println("Lecze ludzi");
    }
}

class RoznoszenieListow implements Pracowac {
    @Override
    public void pracuj() {
        System.out.println("Roznosze Listy");
    }
}

abstract class Pracownik {

    Pracowac pracowac;
    SpedzanieWolnegoCzasu spedzanieWolnegoCzasu;
    Dojezdzac dojezdzac;

    public Pracownik(String zawod) {
        System.out.println(zawod);
    }

    public abstract void wyswietl();

    public void wykonajPrace() {
        pracowac.pracuj();
    }

    public void wykonajDojazd() {
        dojezdzac.dojezdzaj();
    }

    public void wykonajSpedzanieWolnegoCzas() {
        spedzanieWolnegoCzasu.spedzajWolnyCzas();
    }
}

class Listonosz extends Pracownik {
    public Listonosz(String zawod) {
        super(zawod);
        pracowac = new RoznoszenieListow();
        dojezdzac = new Rower();
        spedzanieWolnegoCzasu = new LiteraturaPopularnoNaukowa();
    }
    @Override
    public void wyswietl() {
        System.out.println("Jestem Listonoszem!");
    }
}

class Mechanik extends Pracownik {
    public Mechanik(String zawod) {
        super(zawod);
        pracowac = new NaprawaSamochodow();
        dojezdzac = new Samochod();
        spedzanieWolnegoCzasu = new Silowania();
    }

    @Override
    public void wyswietl() {
        System.out.println("Jestem mechanikiem!");
    }
}

public class SymulatorPracownika {
    public static void main(String[] args) {
        Pracownik Stanislaw = new Listonosz("Listonosz");
        Stanislaw.wykonajSpedzanieWolnegoCzas();
        Stanislaw.wykonajDojazd();
        Stanislaw.wykonajPrace();

    }
}
