public class CashDispenser implements CashDispenser_Interface{

    private final static int INITIAL_COUNT = 500;
    private  int count;
    public CashDispenser(){
        count = INITIAL_COUNT;
    }

    @Override
    public void dispenseCash(int amount) {
        int billsRequired =(int) (amount / 20);
        count -= billsRequired;
    }
    public boolean isSufficientCashAvailable(int amount){
        int billsRequired =(int)(amount / 20);
        if (count >= billsRequired){
            return true;
        }
        else{
            return false;
        }
    }
}
