import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface LatanieInterfejs {
    void lec();
}

class LatamBoMamSkrzydla implements LatanieInterfejs {
    @Override
    public void lec() {
        System.out.println("Lece bo  chce, lece bo życie to sen...!");
    }
}

class IfYouDrinkRocketFuel implements LatanieInterfejs {
    @Override
    public void lec() {
        System.out.println("Hoooouuu-Daaauuuuu");
    }
}

class NieLatam implements LatanieInterfejs {
    @Override
    public void lec() {
        System.out.println("Not this time, Jo.. :( ");
    }
}

interface KwakanieInterfejs {
    void kwacz();
}

class Kwacze implements KwakanieInterfejs {
    @Override
    public void kwacz() {
        System.out.println("KFAKFAKFAK...");
    } //Not sure if it quacks or swears...
}

class Piszczy implements KwakanieInterfejs {
    @Override
    public void kwacz() {
        System.out.println("PIPIPIPIPI...");
    } //still not sure...
}

abstract class Kaczka {

    LatanieInterfejs latanieInterfejs;
    KwakanieInterfejs kwakanieInterfejs;
    public Kaczka() {
    }

    public abstract void wyswietl();

    public void wykonajLec() {
        latanieInterfejs.lec();
    }

    public void wykonajKwacz() {
        kwakanieInterfejs.kwacz();
    }

    public void ustawLatanieInterfejs (LatanieInterfejs li) {
        latanieInterfejs = li;
    }

    public void ustawKwakanieInterfejs (KwakanieInterfejs ki) {
        kwakanieInterfejs = ki;
    }

}

class DzikaKaczka extends Kaczka {
    public DzikaKaczka() {
        kwakanieInterfejs = new Kwacze();
        latanieInterfejs = new LatamBoMamSkrzydla();
    }
    @Override
    public void wyswietl() {
        System.out.println("Jestem wolnym ptakiem!");
    }
}

class GumowaKaczka extends Kaczka {
    public GumowaKaczka() {
        kwakanieInterfejs = new Piszczy();
        latanieInterfejs = new NieLatam();
    }

    @Override
    public void wyswietl() {
        System.out.println("Jestem gumowym ptakiem!");
    }
}

public class MiniSymulatorKaczek {
    public static void main(String[] args) {

        Kaczka dzikaKaczka = new DzikaKaczka();
        Kaczka gumowaKaczka = new GumowaKaczka();
        Kaczka turboGumowaKaczka = new GumowaKaczka();

        List<Kaczka> kaczkiList = Arrays.asList( dzikaKaczka, gumowaKaczka, turboGumowaKaczka );
        Kaczka[] kaczkiTablica = new Kaczka[3];
        kaczkiTablica[0] = dzikaKaczka;
        kaczkiTablica[1] = gumowaKaczka;
        kaczkiTablica[2] = turboGumowaKaczka;
        System.out.println(kaczkiTablica);


        ArrayList<Kaczka> kaczkiArray = new ArrayList<>(kaczkiList);

        for (Kaczka kaczka : kaczkiArray) {
            System.out.println("----------");
            kaczka.wyswietl();
            kaczka.wykonajLec();
            kaczka.wykonajKwacz();
        }
        System.out.println("# ------------- #");
        System.out.println("# Let's do something crazy... #");

        turboGumowaKaczka.ustawLatanieInterfejs(new IfYouDrinkRocketFuel());

        turboGumowaKaczka.wyswietl();
        turboGumowaKaczka.wykonajLec();
        turboGumowaKaczka.wykonajKwacz();
    }
}
