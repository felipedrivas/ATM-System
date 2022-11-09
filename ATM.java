
import java.util.Scanner;
import java.lang.*;

public class ATM implements ATM_Interface {
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;

    private boolean userAuthenticated;
    private int currentAccountNumber = 0;
    private Screen screen = new Screen();
    private Keypad keypad = new Keypad();
    private CashDispenser cashDispenser = new CashDispenser();
    private DepositSlot depositSlot = new DepositSlot();
    private BankDatabase bankDatabase = null;

    public ATM(BankDatabase atmBankDatabase){
        this.userAuthenticated = false;
        this.currentAccountNumber = 0;
        this.bankDatabase = atmBankDatabase;
    }
    @Override
    public void Run() {
        while (true){
            while (!userAuthenticated){
                screen.displayMessageLine("\nWelcome!");
                authenticateUser();
            }
            //user is athenticated here
            performTransactions();
            userAuthenticated = false;
            currentAccountNumber = 0;
            screen.displayMessageLine("\nThank you! Goodbye");
        }
    }
//utility method
    private void authenticateUser(){
        Scanner myScanner = new Scanner(System.in);
        screen.displayMessage("\nPlease enter your account number: ");
        int accountnumber = keypad.getInput();  // Read user input
        screen.displayMessage("\nPlease enter your pin: ");
        int pin = keypad.getInput();
        userAuthenticated = bankDatabase.authenticateUser(accountnumber, pin);

        if (userAuthenticated)
        {
            currentAccountNumber = accountnumber;
        }
        else{
            screen.displayMessageLine("\nInvalid account number or pin. Please try again.");
        }
    }
    private void performTransactions()
    {
        Transaction currentTransactionRef = null;
        boolean userExited  =false;
        while(!userExited){
            int mainMenuSelection = displayMainMenu();
            switch (mainMenuSelection){
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                case DEPOSIT:
                    currentTransactionRef = createTransaction(mainMenuSelection);
                    currentTransactionRef.execute();
                    break;
                case EXIT:
                    screen.displayMessageLine("\nExisting the System...");
                    userExited = true;
                    break;
                default:
                    screen.displayMessageLine("\nYou did not enter a valid selection. Try again");
                    break;
            }
        }
    }
    private int displayMainMenu(){
        screen.displayMessageLine("\nMain Menu: ");
        screen.displayMessageLine("\n1- View my balance ");
        screen.displayMessageLine("\n2- withdraw cash ");
        screen.displayMessageLine("\n3- Deposit funds ");
        screen.displayMessageLine("\n4- Exit\n");
        screen.displayMessageLine("\nEnter a choice:");
        int input = keypad.getInput();
        return input;
    }
    Transaction createTransaction(int type){
        Transaction currentTransactionRef = null;
        switch (type){
            case BALANCE_INQUIRY:
                currentTransactionRef = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
                break;
            case WITHDRAWAL:
                currentTransactionRef = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
                break;
            case DEPOSIT:
                currentTransactionRef = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
                break;
        }
        return currentTransactionRef;
    }
    public static void main(String[] args) {

        ATM myatm =new ATM(new BankDatabase()) ;
        myatm.Run();
    }
}
