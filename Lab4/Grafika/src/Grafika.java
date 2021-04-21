// Zaimplementujemy fałszywy edytor grafiki.  Sam edytor jest uproszczony i zapewnianastępującą funkcjonalność:
// Nekranie widoczny jest tylko jeden kształt, którego nie można usunąć, ale jego para-metry (kolor, wysokość i szerokość)
// można regulować przyciskami; Zmień kolor kształtuna losowy; Zmień wysokość kształtu na losową wartość między 50 a 150;
// Zmień szerokośćkształtu na losową wartość między 50 a 150; Wszystkie operacje można cofnąć za pomocąprzycisku Cofnij .
// Główną ideą implementacji wspomnianego edytora graficznego jestoddzielenie rzeczywistej logiki biznesowej przycisku
// od jego reprezentacji.

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.*;

interface Polecenie {
    void wykonaj();
    void cofnij();
}

class ZmienWysokosc implements Polecenie {
    GraphWindow graphWindow;
    public void PolecenieZmienWysokosc(GraphWindow graphWindow) {
        this.graphWindow = graphWindow;
    }

    public void wykonaj() {
        Random r = new Random();
        int newHeight = r.nextInt(101)+50;
        graphWindow.zmienWysokosc(newHeight);
        System.out.println("Powinna wykonac sie metoda wykonaj");
    }

    public void cofnij() {

    }
}

class ZmienSzerokosc implements Polecenie {
    GraphWindow graphWindow;
    public void PolecenieZmienSzerokosc(GraphWindow graphWindow) {
        this.graphWindow = graphWindow;
    }

    public void wykonaj() {
        Random r = new Random();
        int newWidth = r.nextInt(101)+50;
        graphWindow.zmienSzerokosc(newWidth);
    }

    @Override
    public void cofnij() {

    }
}

class ZmienKolor implements Polecenie {
    GraphWindow graphWindow;
    public void PolecenieZmienKolor(GraphWindow graphWindow) {
        this.graphWindow = graphWindow;
    }

    public void wykonaj() {
        Random r = new Random();
        List<Color> listaKolorow = Arrays.asList( Color.red, Color.blue, Color.green);
        int indeks = r.nextInt(3);
        graphWindow.zmienKolor(listaKolorow.get(indeks));
        System.out.println("Powinna wykonac sie metoda wykonadj");
    }

    @Override
    public void cofnij() {

    }
}

class GraphWindow extends Canvas {
    int width = 100;
    int height = 100;
    Color color = Color.red;
    Graphics graphics;

    public static void makeFrame() {
        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new GraphWindow();
        canvas.setSize(600, 600);
        JButton zmienWysokoscButton = new JButton("Zmien wysokosc");

        zmienWysokoscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ZmienWysokosc zmienWysokosc = new ZmienWysokosc();
                zmienWysokosc.PolecenieZmienWysokosc((GraphWindow) canvas);
                zmienWysokosc.wykonaj();
                System.out.println("Zmien  wysokosc");
            }
        });

        zmienWysokoscButton.setBounds(10,10, 170, 50);
        zmienWysokoscButton.setVisible(true);

        JButton zmienSzerokoscButton = new JButton("Zmien szerokosc");
        zmienSzerokoscButton.setBounds(180,10, 170, 50);
        zmienSzerokoscButton.setVisible(true);

        zmienSzerokoscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Zmien  szerokosc");
            }
        });

        JButton zmienKolorButton = new JButton("Zmien kolor");
        zmienKolorButton.setBounds(350,10, 170, 50);
        zmienKolorButton.setVisible(true);

        zmienKolorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Zmien  kolor");
            }
        });

        frame.add(zmienWysokoscButton);
        frame.add(zmienSzerokoscButton);
        frame.add(zmienKolorButton);

        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

    }

    public void paint(Graphics g) {
        this.graphics = g;
        g.setColor(Color.BLUE);
        g.fillOval(200, 200, 100, 100);
    }

    public void zmienWysokosc(int wysokosc) {
        this.height = wysokosc;
        paint(this.graphics);
    }

    public void zmienSzerokosc(int szerokosc) {
        this.height = szerokosc;
        paint(this.graphics);
    }

    public void zmienKolor(Color color) {
        this.color = color;
        paint(this.graphics);
    }
}


class Grafika extends Canvas {
    public static void main(String[] args) {
        GraphWindow graphWindow = new GraphWindow();
        ZmienWysokosc zmienWysokosc = new ZmienWysokosc();
        zmienWysokosc.PolecenieZmienWysokosc(graphWindow);
        graphWindow.makeFrame();
        graphWindow.paint(graphWindow.graphics);
    }
}