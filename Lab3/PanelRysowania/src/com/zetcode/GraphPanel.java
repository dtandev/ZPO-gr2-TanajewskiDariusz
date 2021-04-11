package com.zetcode;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.ArrayList;

interface Observer {
    void update(int number);
}
interface Observable {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(int number);
}


class GraphPanel implements Observable {

    private ArrayList observers;


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
    public void notifyObservers(int newestNumber) {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer)observers.get(i);
            observer.update(newestNumber);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GraphPanel();
            }
        });
    }

    BufferedImage img = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
    Graphics2D  imgG2 = img.createGraphics();

    JFrame frame = new JFrame("Points");
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();
            // Drawing the image.
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

        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                prev = e.getPoint();
                int X = e.getX();
                int Y = e.getX();
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
            }
            if (SwingUtilities.isRightMouseButton(e)) {
                rButtonDown = false;
            }
        }
    };

    GraphPanel() {
        // RenderingHints let you specify
        // options such as antialiasing.
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