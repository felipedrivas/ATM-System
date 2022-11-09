import java.util.Scanner;

public class Keypad implements Keypad_Interface{
    public int getInput(){
        Scanner myScanner = new Scanner(System.in);

        int inputNumber = myScanner.nextInt();  // Read user input
        return inputNumber;
    }
}
