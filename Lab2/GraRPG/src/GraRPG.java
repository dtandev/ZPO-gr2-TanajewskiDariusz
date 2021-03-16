import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface WalkaInterfejs {
    void walcz();
}

class strzelajZLuku implements WalkaInterfejs {
    @Override
    public void walcz() {
        System.out.println("Jestem Robin Hoodem!");
    }
}

class strzelajZArmaty implements WalkaInterfejs {
    @Override
    public void walcz() {
        System.out.println("Ładuj działo!");
    }
}

class strzelajZPistoletu implements WalkaInterfejs {
    @Override
    public void walcz() {
        System.out.println("Podaj mi magazynek!");
    }
}

class walczMieczem implements WalkaInterfejs {
    @Override
    public void walcz() {
        System.out.println("Mój miecz jest z Tobą!");
    }
}


abstract class Wojownik {

    WalkaInterfejs walkaInterfejs;

    public Wojownik() {
    }

    public abstract void wyswietl();

    public void wykonajWalcz() {
        walkaInterfejs.walcz();
    }

    public void ustawWalkeInterfejs (WalkaInterfejs wi) {
        walkaInterfejs = wi;
    }

}

class strzelec extends Wojownik {
    public strzelec() {
        walkaInterfejs = new strzelajZPistoletu();
    }
    @Override
    public void wyswietl() {
        System.out.println("Jestem strzelcem!");
    }
}

class lucznik extends Wojownik {
    public lucznik() {
        walkaInterfejs = new strzelajZLuku();
    }
    @Override
    public void wyswietl() {
        System.out.println("Jestem lucznikiem!");
    }
}

class rycerz extends Wojownik {
    public rycerz() {
        walkaInterfejs = new walczMieczem();
    }
    @Override
    public void wyswietl() {
        System.out.println("Jestem rycerzem!");
    }
}

class kanonier extends Wojownik {
    public kanonier() {
        walkaInterfejs = new strzelajZArmaty();
    }
    @Override
    public void wyswietl() {
        System.out.println("Jestem kanonierem!");
    }
}

public class GraRPG {
    public static void main(String[] args) {

        Wojownik bogdanBombowy = new kanonier();
        Wojownik robertZchud = new lucznik();
        Wojownik jurandZeSpychowa = new rycerz();
        Wojownik johnRamby = new strzelec();

        List<Wojownik> wojownicyList = Arrays.asList( bogdanBombowy, robertZchud, jurandZeSpychowa, johnRamby );
        ArrayList<Wojownik> bohaterowieArray = new ArrayList<>(wojownicyList);

        for (Wojownik wojownik : bohaterowieArray) {
            System.out.println("----------");
            wojownik.wyswietl();
            wojownik.wykonajWalcz();
        }
        System.out.println("# ------------- #");
        System.out.println("# Give me more power!!! #");

        robertZchud.ustawWalkeInterfejs(new strzelajZArmaty());

        robertZchud.wyswietl();
        robertZchud.wykonajWalcz();

    }
}

