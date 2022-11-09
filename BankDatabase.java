import java.util.ArrayList;

public class BankDatabase implements BankDatabase_Interface{
    private ArrayList<Account> accounts = new ArrayList<Account>();
    public BankDatabase(){
        Account account1 = new Account(12345, 54321, 1000.00, 1200.00);
        Account account2 = new Account(12346, 54320, 1000.00, 1200.00);
        Account account3 = new Account(12347, 54329, 1000.00, 1200.00);
        Account account4 = new Account(12348, 54328, 1000.00, 1200.00);
        Account account5 = new Account(12349, 54327, 1000.00, 1200.00);
        Account account6 = new Account(12350, 54326, 1000.00, 1200.00);
        this.accounts.add(account1);
        this.accounts.add(account2);
        this.accounts.add(account3);
        this.accounts.add(account4);
        this.accounts.add(account5);
        this.accounts.add(account6);
    }

    @Override
    public boolean authenticateUser(int userAccountNumber, int userPIN) {
        Account accountRef = getAccount(userAccountNumber);
        if (accountRef != null) {
            return (accountRef.validatePIN(userPIN));
        } else {
            return false;
        }
    }

    @Override
    public double getAvailableBalance(int userAccountNumber) {
        Account accountRef = getAccount(userAccountNumber);
        return accountRef.getAvailableBalance();
    }

    @Override
    public double getTotalBalance(int userAccountNumber) {
        Account accountRef = getAccount(userAccountNumber);
        return accountRef.getTotalBalance();
    }

    @Override
    public void credit(int userAccountNumber, double amount) {
        Account accountRef = getAccount(userAccountNumber);
        accountRef.credit(amount);
    }

    @Override
    public void debit(int userAccountNumber, double amount) {
        Account accountRef = getAccount(userAccountNumber);
        accountRef.debit(amount);
    }
    private Account getAccount(int userAccountNumber){
        for (var account :accounts) {
            return account;
        }
        return null;
    }
}
