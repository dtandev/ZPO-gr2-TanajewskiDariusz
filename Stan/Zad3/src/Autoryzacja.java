//Zaimplementuj mechanizm autoryzacji.  Poszczególne stany powinny wiedzieć, na jakiinny stan zmodyfikować stan obiektu w
//        zależności od zaistniałych okoliczności.  Samaklasa Autoryzacja powinna dostarczać metod pozwalających na odczyt
//        oraz zmianę ak-tualnego stanu i metody, udostępniających obiekty wszystkich stanów, w jakich może sięznaleźć dana
//        klasa. Głównym stanem klasy Autoryzacja jest SparwdzanieStan – sprawdzaon login oraz hasło i w zależności od wyniku
//        sprawdzania uruchamia stan: Autoryzacja-PoprawnaStan – gdy zarówno login, jak i hasło są poprawne, BladAutoryzacjiStan
//        – gdylogin lub hasło jest błędne, BladAutoryzacji3RazyStan – gdy trzy razy pod rząd podanobłędny login i hasło
//        (jego zadaniem jest zablokowanie możliwości logowania na 30 sekund.Gdy stan ten jest ustawiony, przy pierwszym
//        wywołaniu metody sprawdź (wywołanie tonastępuje z poziomu stanu SprawdzanieStan) czy zainicjowana zostaje
//        zmienna przecho-wująca aktualny czas. Następnie stan ten staje się domyślny na 30 sekund, nie pozwalająctym
//        samym na zalogowanie się. Po upłynięciu 30 sekund stan BladAutoryzacji3RazyStansam ustala stan klasy
//        Autoryzacja ponownie na stan Sparwdzanie-Stan, co pozwala nadokonanie kolejnych prób logowania (po 3
//        nieudanych następuje ponowna blokada na 30sekund)).


// Zamiast klasy Autoryzacji używam klasy Konto

interface State {

    void sprawdzDaneLogowania(String login, String haslo);

}
// SprawdzStan
class SprawdzStan implements State {
    Konto konto;

    public SprawdzStan(Konto konto) {
        this.konto = konto;
    }

    public void sprawdzDaneLogowania(String sprawdzanyLogin, String sprawdzaneHaslo) {
        if (sprawdzanyLogin == this.konto.login & sprawdzaneHaslo == this.konto.haslo) {
            System.out.println("Dane poprawne. Trwa logowanie...");
            konto.setState(konto.autoryzacjaPoprawnaStan);
        } else {
            System.out.println("Nieprawidłowy login lub hasło");
            konto.zmniejszLiczbeProb();
            if (konto.dostepneProbyLogowania==0) {
                konto.setState(konto.bladAutoryzacji3RazyStan);
            } else {
                konto.setState(konto.bladAutoryzacjiStan);
            }
        }
    }

    public String toString() {
        return "Oczekuje na dane logowania...";
    }
}

class AutoryzacjaPoprawnaStan implements State {
    Konto konto;

    public AutoryzacjaPoprawnaStan(Konto konto) {
        this.konto = konto;
    }

    @Override
    public void sprawdzDaneLogowania(String login, String haslo) {
        System.out.println("Użytkownik jest już zalogowany");
    }

    public String toString() {
        return "Uzytkownik jest zalogowany";
    }
}


class BladAutoryzacjiStan implements State {
    Konto konto;

    public BladAutoryzacjiStan(Konto konto) {
        this.konto = konto;
        if (konto.sprawdzLiczbeProb() == 0) {
            konto.setState(konto.bladAutoryzacji3RazyStan);
        }
    }

    public String toString() {
        return "Nieudana próba autoryzacji. Pozostało "+konto.sprawdzLiczbeProb()+" prób.";
    }

    @Override
    public void sprawdzDaneLogowania(String login, String haslo) {
        this.konto.setState(konto.sprawdzStan);
        konto.state.sprawdzDaneLogowania(login, haslo);
    }
}
//BladAutoryzacji3RazyStan
class BladAutoryzacji3RazyStan implements State {
    Konto konto;

    public BladAutoryzacji3RazyStan(Konto konto) {
        this.konto = konto;
    }

    public String toString() {
        return "Konto zablokowane";
    }

    @Override
    public void sprawdzDaneLogowania(String login, String haslo) {
        System.out.println("Konto zablokowane na 30 sekund");
    }
}

class Konto {

    public int dostepneProbyLogowania;
    String login;
    String haslo;

    State sprawdzStan;
    State bladAutoryzacji3RazyStan;
    State bladAutoryzacjiStan;
    State autoryzacjaPoprawnaStan;

    State state;


    public Konto() {
        sprawdzStan = new SprawdzStan(this);
        bladAutoryzacjiStan = new BladAutoryzacjiStan(this);
        bladAutoryzacji3RazyStan = new BladAutoryzacji3RazyStan(this);
        autoryzacjaPoprawnaStan = new AutoryzacjaPoprawnaStan(this);
        dostepneProbyLogowania = 3;
        state = sprawdzStan;
    }

    void setLogin(String login) {
        this.login = login;
    }

    void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void wprowadzDaneLogowania(String login, String haslo) {
        state.sprawdzDaneLogowania(login, haslo);
    }

    void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void zmniejszLiczbeProb() {
        this.dostepneProbyLogowania = this.dostepneProbyLogowania -1;
    }

    public int sprawdzLiczbeProb() {
        return this.dostepneProbyLogowania;
    }


    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("\nKonto użytkownika" );
        result.append("\n");
        result.append("Stan konta :" + state + "\n");
        return result.toString();
    }
}

public class Autoryzacja {
    public static void main(String[] args) {
        Konto mojeKonto = new Konto();
        mojeKonto.setLogin("user1");
        mojeKonto.setHaslo("pass123");
        System.out.println(mojeKonto);

        System.out.println("---mojeKonto---");
        mojeKonto.wprowadzDaneLogowania("user", "pass");
        System.out.println(mojeKonto);
        mojeKonto.wprowadzDaneLogowania("user", "pass");
        System.out.println(mojeKonto);
        mojeKonto.wprowadzDaneLogowania("user", "pass");
        System.out.println(mojeKonto);
        // 4 próba nieudanego logowania
        mojeKonto.wprowadzDaneLogowania("user", "pass");
        System.out.println(mojeKonto);

        System.out.println("---mojeKonto2---");

        Konto mojeKonto2 = new Konto();
        mojeKonto2.setLogin("user1");
        mojeKonto2.setHaslo("pass123");
        System.out.println(mojeKonto2);


        mojeKonto2.wprowadzDaneLogowania("user1", "pass123");
        System.out.println(mojeKonto2);


    }
}