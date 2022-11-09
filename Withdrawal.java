import java.util.Scanner;

public class Withdrawal extends Transaction {
   private final static int CANCELLED = 6;
    private double amount;
    CashDispenser cashDispenser = null;
    Keypad keypad = null;

    public Withdrawal(int accountNumber, Screen atmScreen, BankDatabase atmDatabase, Keypad atmKeypad, CashDispenser atmDispenser) {
        super(accountNumber, atmScreen, atmDatabase);
        this.keypad = atmKeypad;
        this.cashDispenser = atmDispenser;
    }

    @Override
    public void execute() {
        boolean cashDispensed = false;
        boolean transactionCanceled = false;

        BankDatabase bankDatabase = super.getBankDatabase();
        Screen screen = super.getScreen();

        do {
            int selection = displayMenuOfAmounts();
            if (selection != CANCELLED) {
                amount = selection;
                double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
                if (amount <= availableBalance)
                {
                    if (cashDispenser.isSufficientCashAvailable((int) amount))
                    {
                        bankDatabase.debit(getAccountNumber(), amount);
                        cashDispenser.dispenseCash((int) amount);
                        cashDispensed = true;
                        screen.displayMessageLine("\nPlease take your cash from the cash dispenser.");
                    }
                    else
                    {
                        screen.displayMessageLine("\nInsufficient cash available in the ATM" + "\nPlease chpose a smaller amount");
                    }
                }
                else
                {
                    screen.displayMessageLine("\nInsufficient funds in your account" + "\n Please choose a smaller amount");
                }
            }
            else {
                screen.displayMessageLine("\nCancelling transaction...");
                transactionCanceled = true;
            }

        }while (!cashDispensed && !transactionCanceled) ;
    }
    private int displayMenuOfAmounts(){
        int userChoice = 0;
        Screen screen = super.getScreen();
        int [] amounts = new int [] {0, 20, 40, 60, 100, 200};
        while (userChoice ==0)
        {
            screen.displayMessageLine("\nWithdrawal options");
            screen.displayMessageLine("1 - $20");
            screen.displayMessageLine("2 - $40");
            screen.displayMessageLine("3 - $60");
            screen.displayMessageLine("4 - $100");
            screen.displayMessageLine("5 - $200");
            screen.displayMessageLine("6 - Cancel transaction");
            screen.displayMessage("\nChoose a withdrawal option (1-6):");
            int input = keypad.getInput();

            switch (input)
            {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    userChoice = amounts[input];
                    break;
                case CANCELLED:
                    break;
                default:
                    screen.displayMessageLine("\nInvalid selection. Try again");

            }
        }
        return userChoice;
    }
}
