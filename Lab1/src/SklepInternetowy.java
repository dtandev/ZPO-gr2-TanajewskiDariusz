interface NaliczPodatekInterfejs {
    double naliczPodatek(double incoming);
}

class GBTaxes implements NaliczPodatekInterfejs {
    @Override
    public double naliczPodatek(double incoming) {
        System.out.print("Należny podatek wynosi: ");
        return incoming * 0.07;
    }
}

class DETaxes implements NaliczPodatekInterfejs {
    @Override
    public double naliczPodatek(double incoming) {
        System.out.print("Należny podatek wynosi: ");
        return incoming * 0.09;
    }
}

class PLTaxes implements NaliczPodatekInterfejs {
    @Override
    public double naliczPodatek(double incoming) {
        System.out.print("Należny podatek wynosi: ");
        return incoming * 0.06;
    }
}

abstract class OddzialZagranicznySklepu {
    NaliczPodatekInterfejs naliczPodatekInterfejs;

    public OddzialZagranicznySklepu() {
    }

    public abstract void wyswietl();

    public double obliczPodatek(double incoming) {
        return naliczPodatekInterfejs.naliczPodatek(incoming);
    }

    public void ustawLokalizacjeInterfejs(NaliczPodatekInterfejs npi) {
        naliczPodatekInterfejs = npi;
    }
}

class sklepBrytyjski extends OddzialZagranicznySklepu {
    public sklepBrytyjski() {
        naliczPodatekInterfejs = new GBTaxes();
    }

    @Override
    public void wyswietl() {
        System.out.println("Sklep brytyjski!");
    }
}

class sklepNiemiecki extends OddzialZagranicznySklepu {
    public sklepNiemiecki() {
        naliczPodatekInterfejs = new DETaxes();
    }

    @Override
    public void wyswietl() {
        System.out.println("Sklep niemiecki!");
    }
}

class sklepPolski extends OddzialZagranicznySklepu {
    public sklepPolski() {
        naliczPodatekInterfejs = new PLTaxes();
    }

    @Override
    public void wyswietl() {
        System.out.println("Sklep polski!");
    }
}

public class SklepInternetowy {
    public static void main(String[] args) {
        OddzialZagranicznySklepu lidl = new sklepNiemiecki();
        OddzialZagranicznySklepu tesco = new sklepBrytyjski();
        OddzialZagranicznySklepu lewiatan  = new sklepPolski();

        lewiatan.wyswietl();
        System.out.println(lewiatan.obliczPodatek(100.2));

        lidl.wyswietl();
        System.out.println(lidl.obliczPodatek(102.2));
    }
}