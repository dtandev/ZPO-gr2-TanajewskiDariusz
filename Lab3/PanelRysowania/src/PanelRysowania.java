import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

interface Observer {
    void update(int iloscPunktow, int X, int Y,  double distance);
}
interface Observable {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(int iloscPunktow, int X, int Y,  double distance);
}


class IloscPunktowPanel implements Observer {
    private final PanelRysownika panelRysownika;
    int iloscPunktow = 0;

    BufferedImage img = new BufferedImage(150, 50, BufferedImage.TYPE_INT_ARGB);

    JFrame frame = new JFrame("Ilosc Punktow");
    JPanel panel = new JPanel() {
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(img.getWidth(), img.getHeight());
        }
    };

    public void showPanel() {
        panel.setBackground(Color.white);
        frame.setContentPane(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public IloscPunktowPanel(PanelRysownika panelRysownika) {
        this.panelRysownika = panelRysownika;
        panelRysownika.attach(this);
    }

    @Override
    public void update(int iloscPunktow, int X, int Y,  double distance) {
        this.iloscPunktow = iloscPunktow;
        JLabel pointsCounter = new JLabel(String.valueOf(iloscPunktow));
        panel.removeAll();
        panel.add(pointsCounter);
        panel.revalidate();
    }
}

class ListaWspolrzednychPanel implements Observer {
    private final PanelRysownika panelRysownika;
    int X;
    int Y;

    BufferedImage img = new BufferedImage(150, 350, BufferedImage.TYPE_INT_ARGB);

    JFrame frame = new JFrame("Wspolrzedne punktow");
    JPanel panel = new JPanel() {
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(img.getWidth(), img.getHeight());
        }
    };

    public void showPanel() {
        panel.setBackground(Color.white);
        panel.setAutoscrolls(true);
        frame.setContentPane(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public ListaWspolrzednychPanel(PanelRysownika panelRysownika) {
        this.panelRysownika = panelRysownika;
        panelRysownika.attach(this);
    }

    @Override
    public void update(int iloscPunktow, int X, int Y,  double distance) {
        this.X = X;
        this.Y = Y;
        JTextArea textArea = new JTextArea(String.valueOf(X)+" "+String.valueOf(Y));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        panel.add(textArea);
        panel.revalidate();
    }
}

class BarChartSample implements Observer {

    private final PanelRysownika panelRysownika;

    java.util.List<String> punkty = new ArrayList<String>();
    java.util.List<Double> odleglosci = new ArrayList<Double>();

    BufferedImage img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);

    JFrame frame = new JFrame("Bar Chart");
    JPanel panel = new JPanel() {
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(img.getWidth(), img.getHeight());
        }
    };

    public void showPanel() {
        JTextArea textArea = new JTextArea("Nie wiem jak zrobić BarChart, ale wiem jak podać do niego dane");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        panel.add(textArea);
        panel.revalidate();
        panel.setBackground(Color.white);
        panel.setAutoscrolls(true);
        frame.setContentPane(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public BarChartSample(PanelRysownika panelRysownika) {
        this.panelRysownika = panelRysownika;
        panelRysownika.attach(this);
    }

    @Override
    public void update(int iloscPunktow, int X, int Y, double distance) {
        this.punkty.add(String.valueOf(iloscPunktow));
        this.odleglosci.add(distance);
        JTextArea textArea = new JTextArea();
        for (int i = 0; i < punkty.size(); i++) {
            textArea.append("# "+punkty.get(i)+" - " + odleglosci.get(i)+" #\n");
            textArea.setEditable(false);
            textArea.setLineWrap(true);
        }
        textArea.setLineWrap(true);
        panel.add(textArea);
        panel.revalidate();

    }


}

class PanelRysownika implements Observable {

    private ArrayList observers;
    int noOfPoints = 0;
    private int X;
    private int Y;

    public PanelRysownika() {
        observers = new ArrayList();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
        System.out.println(observers.size());
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int iloscPunktow, int X, int Y,  double distance) {
        for (int i = 0; i < observers.size(); i++) {
            distance = Math.sqrt(Math.pow(X-256,2)+Math.pow(Y-256, 2));
            Observer observer = (Observer)observers.get(i);
            observer.update(iloscPunktow, X, Y, Math.round(distance));
        }
    }

    BufferedImage img = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
    Graphics2D  imgG2 = img.createGraphics();

    JFrame frame = new JFrame("Points");
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();
            // Drawing the point.
            int w = img.getWidth();
            int h = img.getHeight();
            g2.drawImage(img, 0, 0, w, h, null);
            g2.dispose();
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(img.getWidth(), img.getHeight());
        }
    };

    MouseAdapter drawer = new MouseAdapter() {
        boolean rButtonDown;
        Point prev;
        double distance = 0.0;

        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                prev = e.getPoint();
                X = e.getX();
                Y = e.getX();
                System.out.println(X);
                System.out.println(Y);
            }
            if (SwingUtilities.isRightMouseButton(e) && !rButtonDown) {
                // (This just behaves a little better
                // than using the mouseClicked event.)
                rButtonDown  = true;
                panel.repaint();
            }
            if (prev != null) {
                Point  next = e.getPoint();
                // We can safely paint to the
                // image any time we want to.
                imgG2.setColor(Color.black);
                imgG2.drawLine(prev.x, prev.y, next.x, next.y);
                // We just need to repaint the
                // panel to make sure the
                // changes are visible
                // immediately.
                panel.repaint();
                prev = next;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                prev = null;
                noOfPoints++;
                System.out.println(noOfPoints);
                notifyObservers(noOfPoints, X, Y, distance);
            }
            if (SwingUtilities.isRightMouseButton(e)) {
                rButtonDown = false;
            }
        }
    };

    public void showPanel() {
        imgG2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        imgG2.setStroke(new BasicStroke(3));
        //
        panel.setBackground(Color.white);
        panel.addMouseListener(drawer);
        panel.addMouseMotionListener(drawer);
        Cursor cursor =
                Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
        panel.setCursor(cursor);
        frame.setContentPane(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}



public class PanelRysowania {
    public static void main(String[] args) {
        PanelRysownika panelRysownika = new PanelRysownika();
        panelRysownika.showPanel();
        IloscPunktowPanel pointsNoPanel = new IloscPunktowPanel(panelRysownika);
        ListaWspolrzednychPanel listaWspolrzednychPanel = new ListaWspolrzednychPanel(panelRysownika);
        pointsNoPanel.showPanel();
        listaWspolrzednychPanel.showPanel();
        BarChartSample barChartSample = new BarChartSample(panelRysownika);
        barChartSample.showPanel();

    }
}
