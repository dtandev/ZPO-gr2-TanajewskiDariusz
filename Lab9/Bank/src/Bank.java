import org.w3c.dom.ls.LSOutput;

interface OperacjaBankowa {
    public void wykonaj();
}

class SystemBankowy {
    OperacjaBankowa operacjaBankowa;

    public SystemBankowy() {}

    public void ustawOperacje(OperacjaBankowa operacjaBankowa) {
        this.operacjaBankowa = operacjaBankowa;
    }

    public void wykonajOperacje() {
        operacjaBankowa.wykonaj();
    }
}


class Account {
    double balance;

    Account() {
        balance = 0.00;
    }

    public void withdrawMoney(double ammount) {
        this.balance = this.balance - ammount;
        System.out.println("Wypłacono: "+ ammount);
    }

    public void depositMoney(double ammount) {
        this.balance = this.balance + ammount;
        System.out.println("Wpłacono: "+ammount);
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
        System.out.println("Rozpoczynam transfer "+amount+ " zł.");
        from.withdrawMoney(amount);
        to.depositMoney(amount);

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


public class Bank {
    public static void main(String[] args) {
        SystemBankowy systemBankowy = new SystemBankowy();
        Account kontoJanaKowalskiego = new Account();
        Account kontoAdamaNowaka = new Account();
        System.out.println("Salda początkowe");
        System.out.println("Saldo Jana Kowalskiego: "+kontoJanaKowalskiego.balance);
        System.out.println("Saldo Adama Nowaka: "+kontoAdamaNowaka.balance);
        Wplata wplata01 = new Wplata(kontoJanaKowalskiego, 3000);
        Wplata wplata02 = new Wplata(kontoAdamaNowaka, 5000);
        systemBankowy.ustawOperacje(wplata01);
        systemBankowy.wykonajOperacje();
        systemBankowy.ustawOperacje(wplata02);
        systemBankowy.wykonajOperacje();
        System.out.println("Salda po wypłacie");
        System.out.println("Saldo Jana Kowalskiego: "+kontoJanaKowalskiego.balance);
        System.out.println("Saldo Adama Nowaka: "+kontoAdamaNowaka.balance);
        Transfer transfer1 = new Transfer(kontoAdamaNowaka, kontoJanaKowalskiego, 1000);
        systemBankowy.ustawOperacje(transfer1);
        systemBankowy.wykonajOperacje();
        System.out.println("Salda po spłacie zadłużenia");
        System.out.println("Saldo Jana Kowalskiego: "+kontoJanaKowalskiego.balance);
        System.out.println("Saldo Adama Nowaka: "+kontoAdamaNowaka.balance);
    }

}

