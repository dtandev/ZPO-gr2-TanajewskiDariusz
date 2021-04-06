interface IPloter {
    void ustalPozycje(int x, int y);
    void rysujDo(int x, int y);
}

interface IMenadzerTuszu {
    void zaladujTusz(int ilosc);
    void pobierzTusz(double ilosc);
    double sprawdzIlosc();
}

class MenedzerTuszu implements IMenadzerTuszu {

    double poziomTuszu = 1000;

    @Override
    public void zaladujTusz(int ilosc) {}

    @Override
    public void pobierzTusz(double ilosc) {}

    @Override
    public double sprawdzIlosc() {
        return poziomTuszu;
    }
}

abstract class SterownikDekorator extends MenedzerTuszu {
    private final MenedzerTuszu menedzerTuszu;

    protected SterownikDekorator(MenedzerTuszu menedzerTuszu) {
        this.menedzerTuszu = menedzerTuszu;
        this.poziomTuszu = menedzerTuszu.poziomTuszu;
    }

    @Override
    public void zaladujTusz(int ilosc) {
        this.poziomTuszu = this.poziomTuszu+ilosc;
    }

    @Override
    public void pobierzTusz(double ilosc) {
        this.poziomTuszu = this.poziomTuszu-ilosc;
        if (this.poziomTuszu < 100) {
            System.out.println("Niski poziom tuszu. Uzupełnij zbiornik");
        }
    }

    @Override
    public double sprawdzIlosc() {
        return this.poziomTuszu;
    }

}

class Ploter implements IPloter {
    int x_position = 0;
    int y_position = 0;
    SterownikDekorator sterownikDekorator = new SterownikDekorator(new MenedzerTuszu()) {
    };

    @Override
    public void ustalPozycje(int x, int y) {
        this.x_position = x;
        this.y_position = y;
    }

    @Override
    public void rysujDo(int x, int y) {
        int x_start = this.x_position;
        int y_start = this.y_position;
        double lineLength = Math.sqrt(Math.pow((x-x_start), 2)+ Math.pow((y-y_start),2));
        sterownikDekorator.pobierzTusz(lineLength);
        this.x_position = x;
        this.y_position = y;

    }
}

public class Drukarka {
    public static void main(String[] args) {
        Ploter canonPloter = new Ploter();
        // Pozycja wyjściowa plotera
        canonPloter.ustalPozycje(0, 0);
        // Sprawdzenie ilosci tuszu
        System.out.println(canonPloter.sterownikDekorator.sprawdzIlosc());
        // Rysowanie linii do współrzędnych xy
        canonPloter.rysujDo(10,0);
        // Sprawdzenie ilości tuszu po rysowaniu
        System.out.println(canonPloter.sterownikDekorator.sprawdzIlosc());
        // Powrót do pozycji wejściowej
        canonPloter.ustalPozycje(0,0);
        // Sprawdzenie ilości tuszu po rysowaniu
        System.out.println(canonPloter.sterownikDekorator.sprawdzIlosc());
        // Doładowanie tonera
        canonPloter.sterownikDekorator.zaladujTusz(10);
        // Sprawdzenie ilości tuszu po rysowaniu
        System.out.println(canonPloter.sterownikDekorator.sprawdzIlosc());
        // Rysowanie linii do współrzędnych xy
        canonPloter.rysujDo(910,0);
        // Sprawdzenie ilości tuszu po rysowaniu
        System.out.println(canonPloter.sterownikDekorator.sprawdzIlosc());
    }
}
