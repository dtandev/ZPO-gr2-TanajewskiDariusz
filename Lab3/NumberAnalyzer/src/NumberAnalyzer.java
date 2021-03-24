import java.util.ArrayList;
import java.util.Scanner;

interface Observer {
    void update(int number);
}
interface Observable {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(int number);
}

class NumberReader implements Observable {

    private ArrayList observers;
    private int newestNumber;

    public NumberReader() {
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
    public void notifyObservers(int newestNumber) {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer)observers.get(i);
            observer.update(newestNumber);
        }
    }

    public int getNumber() {
        int n=1;
        while (n>=0) {
            Scanner in = new Scanner(System.in);
            System.out.println("Wprowadź liczbę: ");
            n = in.nextInt();
            return n;
        }
        return n;
    }

    public int getSolution(int n) {
        int number = n;
        int choice = 0;
        if (n==0) {
            System.out.println("Jakie liczby chcesz przechwytywać? Wybierz właściwy numer");
            System.out.println("1. Większe od zera");
            System.out.println("2. Równe 3");
            System.out.println("3. Podzielne przez 2");
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();
        }
        return choice;
    }

    public void checkNumber (int choice) {
        if (choice == 1) {
            int number = getNumber();
            if (number > 0) {
                notifyObservers(number);
            }
        } else if (choice == 2) {
            int number = getNumber();
            if (number == 3) {
                notifyObservers(number);
            }
        } else if (choice == 3) {
            int number = getNumber();
            if (number % 2 == 0) {
                notifyObservers(number);
            }
        }
         else {}
    }

    public int getNewestNumber() {
        return newestNumber;
    }

    }

class Reader implements Observer {
    private final NumberReader numberReader;
    public int newestNumber=100;

    public Reader(NumberReader numberReader) {
        this.numberReader = numberReader;
        numberReader.attach(this);
    }

    @Override
    public void update(int number) {
        this.newestNumber = number;
        System.out.println(String.format("Pojawiła się liczba spełniająca warunek! Oto ona: %d",  newestNumber));
    }

}

public class NumberAnalyzer {
    public static void main(String[] args) {
        NumberReader nr = new NumberReader();
        Reader reader1 = new Reader(nr);
        nr.checkNumber(nr.getSolution(nr.getNumber()));
    }
}
