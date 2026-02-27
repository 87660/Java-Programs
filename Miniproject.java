
import java.util.Scanner;
public class Miniproject
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int myNumber = (int)(Math.random()*100);
			int userNumber=0;
		do
		{
          System.out.println("Guess my number (1-100): ");
          userNumber = sc.nextInt();
          if(userNumber == myNumber)
          {
          	System.out.println("Yessss , you guessed it right!");
          	break;
          }
          else if(userNumber>myNumber)
          {
          	System.out.println("The number you entered is too large than my number, better luck next time!");
          }
          else
          {
          	System.out.println("The number you entered is too smaller than my number, better luck next time!");
          }
      }
          while(userNumber>=0);
		
		//while(userNumber>=0);
		System.out.println("My number is:" +myNumber);
             sc.close();
	}
     // System.out.println("My number is:");
     // System.out.print(myNumber);
}