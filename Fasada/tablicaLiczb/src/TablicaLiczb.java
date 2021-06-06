class Klawiatura {
    int n;
    Klawiatura(int n) {
        this.n = n;
    }

    void Pobierz() {
        System.out.println("Pobieram tablice składająca się z "+n+" elementów");
    }

    void Sortuj() {
        System.out.println("Sortuję tablicę");
    }
    void Wypisz() {
        System.out.println("Drukuję elementy tablicy na ekranie");
    }

}

class Plik {

    String nazwaPliku;

    Plik(String nazwaPliku) {
        this.nazwaPliku = nazwaPliku;
    }

    void Odczytaj(){
        System.out.println("Otwieram plik "+nazwaPliku);
    }

    void Zamknij() {
        System.out.println("Zamykam plik "+nazwaPliku);
    }
}

class FasadaTablicyLiczb {
    Plik plik;
    Klawiatura klawiatura;

    FasadaTablicyLiczb(Plik plik, Klawiatura klawiatura) {
        this.klawiatura = klawiatura;
        this.plik = plik;
    }

    void obslugaTablicy() {
        System.out.println("Inicjalizacja Fasady Tablicy Liczb");
        plik.Odczytaj();
        klawiatura.Pobierz();
        klawiatura.Sortuj();
        klawiatura.Wypisz();
    }
    void zamkniecieTablicy() {
        plik.Zamknij();
    }
}

public class TablicaLiczb {
    public static void main(String[] args) {
        Klawiatura mojaKlawiatura = new Klawiatura(5);
        Plik dane = new Plik("dane.csv");
        FasadaTablicyLiczb tablicaNLiczb = new FasadaTablicyLiczb(dane, mojaKlawiatura);
        tablicaNLiczb.obslugaTablicy();
        tablicaNLiczb.zamkniecieTablicy();
    }
}
