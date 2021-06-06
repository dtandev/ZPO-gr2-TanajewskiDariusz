class Linia {
    void rysujLinie(int x1, int y1, int x2, int y2) {
        System.out.println("Rysuje linie z punktu ("+x1+","+y1+") do punktu ("+x1+","+y1+")");
    }
}

class Prostokat {
    void rysujProstokat(int x1, int y1, int wysokosc, int szerokosc) {
        System.out.println("Rysuje prostokat z punktu ("+x1+","+y1+"). Prostkat ma szerokosc "+szerokosc+ " i wysokosc "+wysokosc);
    }
}

class FasadaRysowania {
    Linia linia;
    Prostokat prostokat;

    FasadaRysowania(Linia linia, Prostokat prostokat) {
        this.linia = linia;
        this.prostokat = prostokat;
    }

    void rysujFigury() {
        linia.rysujLinie(10,20,30,60);
        prostokat.rysujProstokat(10,20,40,20);
    }
}

public class LiniaProstokat {
    public static void main(String[] args) {
        Linia linia = new Linia();
        Prostokat prostokat = new Prostokat();
        FasadaRysowania fasadaRysowania = new FasadaRysowania(linia, prostokat);
        fasadaRysowania.rysujFigury();
    }
}
