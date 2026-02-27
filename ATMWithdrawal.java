import java.util.Scanner;

class InvalidWithdrawalException extends Exception {
    public InvalidWithdrawalException(String msg) {
       
    }
}

public class ATMWithdrawal {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter balance: ");
        double balance = s.nextDouble();
        System.out.print("Enter withdrawal amount: ");
        double amount = s.nextDouble();

        try {
            if (amount <= 0)
                throw new InvalidWithdrawalException("Amount must be greater than 0.(InvalidWithdrawalException)");
            if (amount > balance)
                throw new InvalidWithdrawalException("Insufficient balance.(InvalidWithdrawalException)");
            balance -= amount;
            System.out.println("Remaining balance: " + balance);
        } catch (InvalidWithdrawalException e) {
            System.out.println(e.getMessage());
        }
    }
}
