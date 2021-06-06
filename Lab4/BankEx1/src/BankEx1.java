
interface OperacjaBankowa {
    void wykonaj(Wyplata... listaWyplat);
}



class Account {
    double balance;
    double debt;
    boolean authorization;
    String pin;

    Account(String PIN) {
        balance = 5000.00;
        debt = 0.00;
        this.authorization = true;
        this.pin = PIN;
    }

    public void withdrawMoney(double ammount) {
        this.balance = this.balance - ammount;
        System.out.println("Wypłacono: "+ ammount);
    }


}

class Wyplata implements OperacjaBankowa {
    Account from;
    double amount;

    public Wyplata(Account from, double amount) {
        this.from = from;
        this.amount = amount;
    }

    public void wykonaj(Wyplata... listaWyplat) {
        for (Wyplata wyplata : listaWyplat) {
            wyplata.from.withdrawMoney(wyplata.amount);
        }
    }
}


public class BankEx1 {
    public static void main(String[] args) {
        Account kontoJanaKowalskiego = new Account("1234");
        System.out.println("Salda początkowe");
        System.out.println("Saldo Jana Kowalskiego: "+kontoJanaKowalskiego.balance);
        Wyplata listaWyplat = new Wyplata(kontoJanaKowalskiego, 0);
        listaWyplat.wykonaj(new Wyplata(kontoJanaKowalskiego,100), new Wyplata(kontoJanaKowalskiego, 200));

        // Wykonanie wszystkich operacji
        System.out.println("Salda po wypłatach");
        System.out.println("Saldo Jana Kowalskiego: "+kontoJanaKowalskiego.balance);
    }
}