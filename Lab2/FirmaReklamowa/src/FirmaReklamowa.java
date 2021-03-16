
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

interface JezykInterface {
    void show();
    void preferowanyJezyk();
}

interface WysylkaInterface {
    void wyslij();
}

class poAngielsku implements JezykInterface {
    @Override
    public void preferowanyJezyk() {
        System.out.println("I speak english!");
    }

    @Override
    public void show() {
        System.out.println("Buy it");
    }
}

class poNiemiecku implements JezykInterface {
    @Override
    public void preferowanyJezyk() {
        System.out.println("Ich spreche Deutch!");
    }

    @Override
    public void show() {
        System.out.println("Kauf es");
    }
}

class poFrancusku implements JezykInterface {
    @Override
    public void preferowanyJezyk() {
        System.out.println("Je parle français");
    }

    @Override
    public void show() {
        System.out.println("Achète-le!");
    }
}

class wyslijSms implements WysylkaInterface {
    @Override
    public void wyslij() {
        System.out.println("Wysylka smsem");
    }
}

class wyslijEmail implements WysylkaInterface {
    @Override
    public void wyslij() {
        System.out.println("Wysylka emailem");
    }
}

class wyslijGlosowa implements WysylkaInterface {
    @Override
    public void wyslij() {
        System.out.println("Wiadomosc głosowa");
    }
}

abstract class Reklama {

    JezykInterface jezykInterface;
    WysylkaInterface wysylkaInterface;

    public void ustawJezykInterface (JezykInterface ji) {
        jezykInterface = ji;
    }

    public void ustawWysylkaInterface (WysylkaInterface wi) {
        wysylkaInterface = wi;
    }

    public void wyswietlReklame() {
        jezykInterface.show();
    }

    public void wyslijReklame() {
        wysylkaInterface.wyslij();
    }

    public void wyswietl() {
        System.out.println("To jest reklama.");
    }
}

class HamburgerAdv extends Reklama {
    public HamburgerAdv() {
        jezykInterface = new poAngielsku();
        wysylkaInterface = new wyslijEmail();
    }
    public void wyswietl() {
        System.out.println("This is the bigger Hamburger on the World");
    }
}

class IceCreamAdv extends Reklama {
    public IceCreamAdv() {
        jezykInterface = new poAngielsku();
        wysylkaInterface = new wyslijEmail();
    }
    public void wyswietl() {
        System.out.println("This is the tastiest Ice Cream on the World");
    }
}

class CarAdv extends Reklama {
    public CarAdv() {
        jezykInterface = new poAngielsku();
        wysylkaInterface = new wyslijEmail();
    }
    public void wyswietl() {
        System.out.println("This is the fastest Car on the World");
    }
}

abstract class Klient {

    JezykInterface jezykInterface;

    public void ustawJezykInterface (JezykInterface ji) {
        jezykInterface = ji;
    }

    public void ustawPreferowanyJezyk() {
        jezykInterface.preferowanyJezyk();
    }

    public void wyswietl() {
        System.out.println("Jestem klientem.");
    }
}


class American extends Klient {
    public American() {
        jezykInterface = new poAngielsku();
    }

    @Override
    public void wyswietl() {
        System.out.println("I'm American!");
    }
}

class Deutscher extends Klient {
    public Deutscher() {
        jezykInterface = new poNiemiecku();
    }

    @Override
    public void wyswietl() {
        System.out.println("Ich bin Deutscher!");
    }
}

class Francais extends Klient {
    public Francais() {
        jezykInterface = new poFrancusku();
    }

    @Override
    public void wyswietl() {
        System.out.println("Je suis Français!");
    }
}

public class FirmaReklamowa {
    public static void main(String[] args) {

        Klient johnSmith = new American();
        Klient hansKloss = new Deutscher();
        Klient robertPires = new Francais();

        Klient[] clientsTable = new Klient[3];
        clientsTable[0] = johnSmith;
        clientsTable[1] = hansKloss;
        clientsTable[2] = robertPires;
        System.out.println(clientsTable);

        Reklama car = new CarAdv();
        Reklama iceCream = new IceCreamAdv();
        Reklama hamburger = new HamburgerAdv();

        Reklama[] advTable = new Reklama[3];
        advTable[0] = car;
        advTable[1] = iceCream;
        advTable[2] = hamburger;
        System.out.println(advTable);


        Random generator = new Random();
        // Losowanie klienta i wyswietlanie informacji o nim
        int clientIndex = generator.nextInt(clientsTable.length);
        clientsTable[clientIndex].wyswietl();

        // Losowanie reklamy i wyswietlanie informacji o produkcie
        int advIndex = generator.nextInt(clientsTable.length);
        advTable[advIndex].wyswietl();

        // Wyświetlenie "Kup to!" w języku właściwym dla wylosowanego klienta
        advTable[advIndex].ustawJezykInterface(clientsTable[clientIndex].jezykInterface);
        advTable[advIndex].wyswietlReklame();
    }
}
