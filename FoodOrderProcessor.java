import java.util.Scanner;

public class FoodOrderProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of orders: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            try {
                System.out.print("Enter OrderID and Amount: ");
                String[] parts = sc.nextLine().split(" ");

                String orderId = parts[0];
                double amount = Double.parseDouble(parts[1]);

                System.out.println("Order Accepted: ID = " + orderId + ", Amount = " + amount);
            } 
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error: Missing OrderID or Amount.(ArrayIndexOutOfBoundsException)");
            } 
            catch (NumberFormatException e) {
                System.out.println("Error: Amount must be a number.(NumberFormatException)");
            }
        }
    }
}