public class Transaction implements Transaction_interface{
    private int accountNumber;
    private BankDatabase bankDatabase = null;
    private Screen screen = null;

    public Transaction(int userAccountNumber, Screen atmScreen, BankDatabase atmBankdatabase){
        accountNumber = userAccountNumber;
        this.screen = atmScreen;
        this.bankDatabase = atmBankdatabase;
    }
     public void execute(){

    }

    @Override
    public int getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public BankDatabase getBankDatabase() {
        return this.bankDatabase;
    }

    @Override
    public Screen getScreen() {
        return screen;
    }

}
