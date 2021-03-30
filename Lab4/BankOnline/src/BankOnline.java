class Bank {

    void income(){};
    void transfer(){};
    void interestChange(){};

    public Account[] bankAccounts = new Account[3];

}

class Account {
    long Balance;
    Interest interest = new Interest();

    void doOperation(Operation operation){
        operation.execute();
    };

}

class Operation {
    void execute() {};
}

class InterestChange extends Operation{
    Account account;
    Interest interest;

    void execute() {};
}

class Income extends Operation{
    Account account;
    long incomeAmount;

    public Income(int i) {
        this.incomeAmount = i;
    }

    void execute(
    ) {
        System.out.print("Zwieksz saldo konta");
    };
}

class Transfer extends Operation{
    Account from;
    Account to;

    void execute() {};
}

class Interest {
    void compute(){};
}

class interestA extends Interest {}

class interestB extends Interest {}

class interestC extends Interest {}


public class BankOnline {

    public static void main(String[] args) {
        Bank IdeaBank = new Bank();
        Bank SantanderBank = new Bank();

        IdeaBank.bankAccounts = new Account[1];
        SantanderBank.bankAccounts = new Account[1];

        IdeaBank.bankAccounts[0] = new Account();
        IdeaBank.bankAccounts[0].Balance = 1000;
        IdeaBank.bankAccounts[0].interest = new interestA();

        SantanderBank.bankAccounts[0] = new Account();
        SantanderBank.bankAccounts[0].Balance = 2000;
        SantanderBank.bankAccounts[0].interest = new interestB();

        Operation o = new Income(2000);
        IdeaBank.bankAccounts[0].doOperation(o);

    }
}
