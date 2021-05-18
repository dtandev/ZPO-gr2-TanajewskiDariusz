import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;

interface OperacjaBankowa {
    public void wykonaj();
}

class SystemBankowy {
    ArrayList <OperacjaBankowa> operacjeBankowa;

    public SystemBankowy() {
        operacjeBankowa = new ArrayList<OperacjaBankowa>();
    }

    public void ustawOperacje(OperacjaBankowa operacjaBankowa) {
        this.operacjeBankowa.add(operacjaBankowa);
    }

    public void wykonajOperacje() {
        for (OperacjaBankowa i : operacjeBankowa) {
            i.wykonaj();
        }
    }

}


class Account {
    double balance;
    double debt;
    ArrayList<Transfer> zleceniaStale;
    boolean authorization;
    String pin;

    Account(String PIN) {
        balance = 0.00;
        debt = 0.00;
        zleceniaStale = new ArrayList<Transfer>();
        this.authorization = true;
        this.pin = PIN;
    }

    public void withdrawMoney(double ammount) {
        this.balance = this.balance - ammount;
        System.out.println("Wypłacono: "+ ammount);
    }

    public void depositMoney(double ammount) {
        this.balance = this.balance + ammount;
        System.out.println("Wpłacono: "+ammount);
    }

    public void showBalance() {
        System.out.println("Obecny stan konta: "+balance);
    }

    public void borrowMoney (double amount) {
        this.debt = this.debt+amount;
        this.balance = this.balance+amount;
    }

    public void returnMoney (double amount) {
        this.debt = this.debt-amount;
        this.balance = this.balance-amount;
    }

    public void dodajZlecenieStale(Transfer transfer) {
        this.zleceniaStale.add(transfer);
    }

    public String getPin() {
        return this.pin;
    }

}


class Transfer implements OperacjaBankowa {
    Account from;
    Account to;

    double amount;

    public Transfer(Account from, Account to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public void wykonaj() {
        if (from.authorization) {
            Scanner pinReader = new Scanner(System.in);  // Create a Scanner object
            System.out.println("W celu autoryzacji wprowadź kod PIN:");
            String PIN = pinReader.nextLine();  // Read user input

            if (PIN.equals(from.getPin())) {
                System.out.println("Rozpoczynam transfer "+amount+ " zł.");
                from.withdrawMoney(amount);
                to.depositMoney(amount);
            } else {
                System.out.println("Nieprawidłowy PIN. Operacja odrzucona.");
            }
        } else {
            System.out.println("Rozpoczynam transfer "+amount+ " zł.");
            from.withdrawMoney(amount);
            to.depositMoney(amount);
        }
    }

}
class Wplata implements OperacjaBankowa{
    Account to;
    double amount;

    public Wplata(Account to, double amount) {
        this.to = to;
        this.amount = amount;
    }

    public void wykonaj() {
        to.depositMoney(amount);
    }
}

class Wyplata implements OperacjaBankowa {
    Account from;
    double amount;

    public Wyplata(Account from, double amount) {
        this.from = from;
        this.amount = amount;
    }

    public void wykonaj() {
        from.withdrawMoney(amount);
    }
}

class SprawdzenieStanuKonta implements OperacjaBankowa {
    Account account;

    public SprawdzenieStanuKonta(Account account) {
        this.account = account;
    }

    public void wykonaj() {
        account.showBalance();
    }
}


class UstawienieZlecen implements OperacjaBankowa {
    Account account;

    public void SprawdzenieStanuKonta(Account account) {
        this.account = account;
    }

    public void wykonaj() {
        account.showBalance();
    }
}

public class BankEx4 {
    public static void main(String[] args) {
        SystemBankowy systemBankowy = new SystemBankowy();
        Account kontoJanaKowalskiego = new Account("1234");
        Account kontoAdamaNowaka = new Account("4321");
        System.out.println("Salda początkowe");
        System.out.println("Saldo Jana Kowalskiego: "+kontoJanaKowalskiego.balance);
        System.out.println("Saldo Adama Nowaka: "+kontoAdamaNowaka.balance);
        Wplata wplata01 = new Wplata(kontoJanaKowalskiego, 3000);
        Wplata wplata02 = new Wplata(kontoAdamaNowaka, 5000);
        systemBankowy.ustawOperacje(wplata01);
        systemBankowy.ustawOperacje(wplata02);

        Transfer transfer1 = new Transfer(kontoAdamaNowaka, kontoJanaKowalskiego, 1000);
        systemBankowy.ustawOperacje(transfer1);

        // Wykonanie wszystkich operacji
        systemBankowy.wykonajOperacje();
        System.out.println("Salda po spłacie zadłużenia");
        System.out.println("Saldo Jana Kowalskiego: "+kontoJanaKowalskiego.balance);
        System.out.println("Saldo Adama Nowaka: "+kontoAdamaNowaka.balance);
    }

}
