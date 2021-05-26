interface State {

    void wlaczTV();
    void wylaczTV();
    void ustawKanal(int kanal);
}

class WylaczonyTV implements State {
    TV tv;

    public WylaczonyTV(TV tv) {
        this.tv = tv;
    }

    public void wlaczTV() {
        System.out.println("Wlaczam TV");
    }

    public void wylaczTV() {
        System.out.println("Telewizor jest juz wylaczony");
    }

    public void ustawKanal(int kanal) {
        System.out.println("Nie mozna zmienic kanalu na wylaczonym TV");
    }

    public String toString() {
        return "wylaczony";
    }
}

class WlaczonyTV implements State {
    TV tv;

    public WlaczonyTV(TV tv) {
        this.tv = tv;
    }

    public void wylaczTV() {
        System.out.println("Wyłączam TV");
    }

    public void wlaczTV() {
        System.out.println("TV jest juz wlaczony");
    }

    public void ustawKanal(int kanal) {
        if (tv.kanal == kanal) {
            System.out.println("Nie mozna zmienic na kanal " + kanal +". Kanal juz jest ustawiony");
        } else {
            System.out.println("Zmieniam kanal na "+ kanal);
            tv.kanal = kanal;
        }
    }

    public String toString() {
        return "wlaczony";
    }
}


class TV {

    State wlaczonyTV;
    State wylaczonyTV;

    State state = wylaczonyTV;
    int kanal;

    public TV() {
        wlaczonyTV = new WlaczonyTV(this);
        wylaczonyTV = new WylaczonyTV(this);
        state = wylaczonyTV;
    }

    public void wlaczTv() {
        state.wlaczTV();
        setState(wlaczonyTV);
        zmienKanal(1);
    }

    public void wylaczTv() {
        state.wylaczTV();
        setState(wylaczonyTV);
    }

    public void zmienKanal(int kanal) {
        state.ustawKanal(kanal);
    }

    void sprawdzKanal() {
        System.out.print("Aktualny kanał: ");
        System.out.println(this.kanal);
    }

    void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }


    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("\nSamsung" );
        result.append("\nDigitally Yours");
        result.append("\n");
        result.append("Stan TV :" + state + "\n");
        return result.toString();
    }
}

public class Pilot {
    public static void main(String[] args) {
        TV mojTv = new TV();

        System.out.println(mojTv);
        mojTv.zmienKanal(10);
        mojTv.wlaczTv();
        mojTv.zmienKanal(1);
        mojTv.sprawdzKanal();
        mojTv.zmienKanal(10);
        System.out.println(mojTv);
        mojTv.wylaczTv();
        System.out.println(mojTv);

    }
}
