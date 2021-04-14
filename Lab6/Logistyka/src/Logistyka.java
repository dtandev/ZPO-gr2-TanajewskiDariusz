abstract class DostarczTowar {
    void pakuj() {
        System.out.println("Towar zapakowany");
    }
    void dowiez() {
        System.out.println("Towar dowieziony");
    }

    void wyladuj() {
        System.out.println("Towar wyladowany");
    }
}

class TransportFactory {

    public static DostarczTowar rodzajTransportu(String rodzajTransportu) {
        DostarczTowar dostarczTowar = null;

        if (rodzajTransportu.equals("Ciezarowka")) {
            dostarczTowar = new Ciezarowka();
        } else if (rodzajTransportu.equals("Statek")) {
            dostarczTowar = new Statek();
        } else if (rodzajTransportu.equals("pickup")) {
            dostarczTowar = new Samolot();
        } else {
            System.out.println("Nie ma możliwości wysylki w taki sposób");
        }
        return dostarczTowar;
    }
}


class Ciezarowka extends DostarczTowar {
    public void dowiez() {
        System.out.println("Dostarcz towar za pomoca ciezarowki");
    }
}

class Statek extends DostarczTowar {
    public void dowiez() {
        System.out.println("Dostarcz towar za pomoca statku");
    }
}

class Samolot extends DostarczTowar {
    public void dowiez() {
        System.out.println("Dostarcz towar za pomoca samolotu");
    }
}

class FirmaLogistyczna {
    TransportFactory transportFactory;

    public FirmaLogistyczna(TransportFactory transportFactory) {
        this.transportFactory = transportFactory;
    }

    public void dostarczTowary (String type) {
        DostarczTowar dostarczTowar;
        dostarczTowar = TransportFactory.rodzajTransportu(type);
        dostarczTowar.pakuj();
        dostarczTowar.dowiez();
        dostarczTowar.wyladuj();
    }
}

public class Logistyka {
    public static void main(String[] args) {
        TransportFactory factory = new TransportFactory();
        FirmaLogistyczna inWroclaw = new FirmaLogistyczna(factory);
        inWroclaw.dostarczTowary("Ciezarowka");

        // Wystarczy dodac nowa klase z rodzajem transportu
    }
}