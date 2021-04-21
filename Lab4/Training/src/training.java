interface Polecenie {
    void wykonaj();
}

class PolecenieDodajElementy implements Polecenie {
    Kalkulator kalkulator;
    public PolecenieDodajElementy(Kalkulator kalkulator) {
        this.kalkulator = kalkulator;
    }

    public void wykonaj() {
        kalkulator.dodaj();
    }
}

class PolecenieOdejmijElementy implements Polecenie {
    Kalkulator kalkulator;
    public PolecenieOdejmijElementy(Kalkulator kalkulator) {
        this.kalkulator = kalkulator;
    }

    public void wykonaj() {
        kalkulator.odejmij();
    }
}

class Kalkulator {
    int[] b;
    public Kalkulator(int...args) {
        this.b = args;
    }

    public void dodaj() {

        int wynik = 0;

        for(int i=0; i<b.length; i++) {

            wynik += b[i];
        }
        System.out.println("Wynik dodawnia: "+wynik);
    }

    public void odejmij() {

        int wynik = 0;

        for(int i=0; i<b.length; i++) {

            wynik -= b[i];
        }
        System.out.println("Wynik odejmowania: "+wynik);
    }
}

class SimpleRemoteControl {
    Polecenie polecenie;

    public SimpleRemoteControl() {}

    public void wybierzPolecenie(Polecenie polecenie) {
        this.polecenie = polecenie;
    }

    public void wykonajPolecenie() {
        polecenie.wykonaj();
    }
}

public class training {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Kalkulator kalkulator = new Kalkulator(1,2);
        PolecenieDodajElementy dodajElementy = new PolecenieDodajElementy(kalkulator);
        PolecenieOdejmijElementy odejmijElementy = new PolecenieOdejmijElementy(kalkulator);
        remote.wybierzPolecenie(dodajElementy);
        remote.wykonajPolecenie();
        remote.wybierzPolecenie(odejmijElementy);
        remote.wykonajPolecenie();
    }
}
