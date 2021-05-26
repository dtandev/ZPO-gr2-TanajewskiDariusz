interface State {

    public void malujKredkami();
    public void malujFarbami();
    public void malujMazakami();
}

class PlotnoSuche implements State {
    PlotnoMalarskie plotnoMalarskie;

    public PlotnoSuche(PlotnoMalarskie plotnoMalarskie) {
        this.plotnoMalarskie = plotnoMalarskie;
    }

    public void malujKredkami() {
        System.out.println("Malujesz kredkami");
    }

    public void malujFarbami() {
        System.out.println("Malujesz farbami");
    }

    public void malujMazakami() {
        System.out.println("Malujesz mazakami");
    }

    public String toString() {
        return "suche";
    }
}

class PlotnoWilgotne implements State {
    PlotnoMalarskie plotnoMalarskie;

    public PlotnoWilgotne(PlotnoMalarskie plotnoMalarskie) {
        this.plotnoMalarskie = plotnoMalarskie;
    }

    public void malujKredkami() {
        System.out.println("Nie mozesz malowac kredkami po wilgotnym plotnie");
    }

    public void malujFarbami() {
        System.out.println("Malujesz farbami");
    }

    public void malujMazakami() {
        System.out.println("Nie mozesz malowac mazakami");
    }

    public String toString() {
        return "wilgotne";
    }
}


class PlotnoMalarskie {

    State plotnoSuche;
    State plotnoWilgotne;

    State state = plotnoSuche;
    int zawilgocenie = 0;

    public PlotnoMalarskie(int zawilgocenie) {
        plotnoSuche = new PlotnoSuche(this);
        plotnoWilgotne = new PlotnoWilgotne(this);
        state = plotnoSuche;

        // jesli zawilgocenie plotna jest wieksze niz 30%, zmien stan na wilgotne
        this.zawilgocenie = zawilgocenie;
        if (zawilgocenie > 30) {
            state = plotnoWilgotne;
            System.out.println("test");
        }
    }

    public void malujKredkami() {
        state.malujKredkami();
    }

    public void malujFarbami() {
        state.malujFarbami();
        zwiekszZawilgocenie(10);
        if (sprawdzWilgotnosc()>30) {
            setState(plotnoWilgotne);
        } else {
            setState(plotnoSuche);
        }
    }



    public void malujMazakami() {
        state.malujMazakami();
        zwiekszZawilgocenie(1);
        if (sprawdzWilgotnosc()>30) {
            setState(plotnoWilgotne);
        } else {
            setState(plotnoSuche);
        }
    }

    int sprawdzWilgotnosc () {
        return this.zawilgocenie;
    }

    void setState(State state) {
        this.state = state;
    }

    void zwiekszZawilgocenie(int wiecejWilgoci) {
        this.zawilgocenie = this.zawilgocenie+wiecejWilgoci;
    }

    void zmniejszZawilgocenie(int mniejWilgoci) {
        this.zawilgocenie = this.zawilgocenie-mniejWilgoci;
    }

    void podmuchaj() {
        zmniejszZawilgocenie(10);
        if (this.zawilgocenie <= 30) {
            state = plotnoSuche;
        } else {
            state = plotnoWilgotne;
        }
    }

    public State getState() {
        return state;
    }


    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("\nPlotno Malarskie LeonardoDaVinci Inc.");
        result.append("\nZawilgocenie: " + zawilgocenie + " %");
        result.append("\n");
        result.append("Plotno malarskie jest" + state + "\n");
        return result.toString();
    }
}

public class Paint {
    public static void main(String[] args) {
        PlotnoMalarskie mojePlotno = new PlotnoMalarskie(0);

        System.out.println(mojePlotno);
        mojePlotno.malujFarbami();
        mojePlotno.malujKredkami();
        mojePlotno.malujFarbami();
        mojePlotno.malujFarbami();
        mojePlotno.malujMazakami();
        mojePlotno.malujKredkami();

        System.out.println(mojePlotno);

    }
}
