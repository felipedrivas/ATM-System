public class BalanceInquiry extends Transaction{
    public BalanceInquiry(int accountNumber, Screen atmScreen, BankDatabase atmDatabase){
super(accountNumber, atmScreen, atmDatabase);

    }

    @Override
    public void execute() {
        int accountNumber = getAccountNumber();
        Screen screen = getScreen();
        BankDatabase database = getBankDatabase();

        double availableBalance = database.getAvailableBalance(accountNumber);
        double totalBalance = database.getTotalBalance(accountNumber);

        screen.displayMessageLine("\nBalance information: ");
        screen.displayMessage(" - Available balance: ");
        screen.displayDollarAmount(availableBalance);
        screen.displayMessage("\n - Total balance: ");
        screen.displayDollarAmount(totalBalance);
        screen.displayMessageLine(" ");

    }
}
