public class Account implements Account_Interface {
    private int accountNumber;
    private int pin;
    private double availableBalance;
    private double totalBalance;
    public Account(int userAccountNumber, int userPin, double userAvailableBalance, double userTotalBalance){
        this.accountNumber =userAccountNumber;
        this.pin = userPin;
        this.availableBalance = userAvailableBalance;
        this.totalBalance = userTotalBalance;

    }
    public int getAccountNumber(){
        return accountNumber;
    }
    public boolean validatePIN(int userPIN){
        if (pin == userPIN)
        {
            return true;
        }
        else{
            return false;
        }
    }
    public double getAvailableBalance(){
        return availableBalance;
    }
    public double getTotalBalance(){
        return totalBalance;
    }
    public void credit(double amount){
        totalBalance+=amount;
    }
    public void debit(double amount){
        availableBalance -=amount;
        totalBalance -=amount;
    }
}
