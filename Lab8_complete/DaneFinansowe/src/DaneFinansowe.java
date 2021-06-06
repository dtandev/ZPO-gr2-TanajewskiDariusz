import java.util.Dictionary;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

enum NazwyMiesiecy {

    STYCZEN, LUTY, MARZEC, KWIECIEN, MAJ, CZERWIEC, LIPIEC, SIERPIEN, WRZESIEN, PAZDZIERNIK, LISTOPAD, GRUDZIEN
}

final class Miesiac {
    private static Map<NazwyMiesiecy, Miesiac> miesiace;
    private final NazwyMiesiecy miesiac;
    private double plan;
    private double wykonanie;

    static {
        miesiace = new ConcurrentHashMap<>();
        miesiace.put(NazwyMiesiecy.STYCZEN, new Miesiac(NazwyMiesiecy.STYCZEN));
        miesiace.put(NazwyMiesiecy.LUTY, new Miesiac(NazwyMiesiecy.LUTY));
        miesiace.put(NazwyMiesiecy.MARZEC, new Miesiac(NazwyMiesiecy.MARZEC));
        miesiace.put(NazwyMiesiecy.KWIECIEN, new Miesiac(NazwyMiesiecy.KWIECIEN));
        miesiace.put(NazwyMiesiecy.MAJ, new Miesiac(NazwyMiesiecy.MAJ));
        miesiace.put(NazwyMiesiecy.CZERWIEC, new Miesiac(NazwyMiesiecy.CZERWIEC));
        miesiace.put(NazwyMiesiecy.LIPIEC, new Miesiac(NazwyMiesiecy.LIPIEC));
        miesiace.put(NazwyMiesiecy.SIERPIEN, new Miesiac(NazwyMiesiecy.SIERPIEN));
        miesiace.put(NazwyMiesiecy.WRZESIEN, new Miesiac(NazwyMiesiecy.WRZESIEN));
        miesiace.put(NazwyMiesiecy.PAZDZIERNIK, new Miesiac(NazwyMiesiecy.PAZDZIERNIK));
        miesiace.put(NazwyMiesiecy.LISTOPAD, new Miesiac(NazwyMiesiecy.LISTOPAD));
        miesiace.put(NazwyMiesiecy.GRUDZIEN, new Miesiac(NazwyMiesiecy.GRUDZIEN));
    }

    private Miesiac(NazwyMiesiecy miesiac) {
        this.miesiac = miesiac;
    }

    public static Miesiac getInstance(NazwyMiesiecy miesiac) {
        return miesiace.get(miesiac);
    }

    public NazwyMiesiecy getName() {
        return miesiac;
    }

    public double getPlan() {
        return plan;
    }

    public double getWykonanie() {
        return wykonanie;
    }

    public void setPlan(double newPlan) {
        this.plan = newPlan;
    }

    public void setWykonanie(double newWykonanie) {
        this.wykonanie = newWykonanie;
    }

    public String ToString() {
        return "My type is " + this.miesiac;
    }
}

public class DaneFinansowe {

    public static void main(String[] args) {
        // Każdy miesiąc w roku jest odrębną instancją klasy Miesiąc
        Miesiac m1_2020 = Miesiac.getInstance(NazwyMiesiecy.STYCZEN);
        m1_2020.setPlan(3000);
        m1_2020.setWykonanie(2000);

        Miesiac m2_2020 = Miesiac.getInstance(NazwyMiesiecy.LUTY);
        m2_2020.setPlan(12000);
        m2_2020.setWykonanie(9000);

        System.out.println("-------RAPORT--------");

        System.out.println("Wykonanie w miesiacu "+m2_2020.getName()+ " wyniosło "+ m2_2020.getWykonanie()+" i stanowiło "
                +m2_2020.getWykonanie()/ m2_2020.getPlan()*100 + "% planu");
    }
}
