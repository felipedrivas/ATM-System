public class Deposit extends Transaction{
private final static int CANCELLED = 6;
private double amount;
private Keypad keypad =null;
private DepositSlot depositSlot = null;

public Deposit(int accountNumber, Screen atmScreen, BankDatabase atmDatabase, Keypad atmKeypad, DepositSlot atmDepositSlot){
    super(accountNumber, atmScreen, atmDatabase);
    this.keypad = atmKeypad;
    this.depositSlot = atmDepositSlot;
}

    @Override
    public void execute() {
        BankDatabase bankDatabase = super.getBankDatabase();
        Screen screen = super.getScreen();

        amount = promptForDepositAmount();

        if(amount!= CANCELLED){
            screen.displayMessage("\nPlease insert a deposit envelope containing");
            screen.displayDollarAmount(amount);
            screen.displayMessageLine(" in the deposit slot");

            boolean envelopeReceived = depositSlot.isEnvelopeReceived();
            if(envelopeReceived){
                screen. displayMessageLine("\nYour envelope has been received." +
                                "\nNOTE: The money depoisted will not be available until"+
                        "\n we verify the amount of any enchlosed cash, and any enclosed " +
                        "checks clear.");
                bankDatabase.credit(getAccountNumber(), amount);
            }
            else {
                screen.displayMessageLine("you did not insert an envelope, "
                        +" so the ATM has cancelled your transaction");
            }
        }
        else {
            screen.displayMessageLine("Cancelling transaction...");
        }

    }
    private double promptForDepositAmount(){
    Screen screen= super.getScreen();
    screen.displayMessage("\nPlease enter a deposit amount in "+ "CENTS (or 0 to Cancel): ");
    int input = keypad.getInput();
    if (input==CANCELLED){
        return CANCELLED;
    }
    else {
        return (double) input;
    }
}
}
