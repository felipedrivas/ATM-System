public interface Transaction_interface {
    void execute();
    int getAccountNumber();
    BankDatabase getBankDatabase();
    Screen getScreen();
}
