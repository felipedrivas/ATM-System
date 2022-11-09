public interface Account_Interface {
    int getAccountNumber();
    boolean validatePIN(int userPIN);
    double getAvailableBalance();
    double getTotalBalance();
    void credit(double amount);
    void debit(double amount);
}
