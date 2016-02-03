package opdracht1;
import java.util.Scanner;	

public class leapyear {
	public static void main(String[] args) {
		//Setting up user input
		Scanner input = new Scanner(System.in);
		
		//Prompt
		System.out.println("enter the year you wish to evaluate");
				
		//Write input to variable		
		int value = input.nextInt();
		
		//Evaluate value using leap year loop and output 
		if (value%4 != 0) {
			System.out.println(value + " is a common year");
		}
		else if(value%100 !=0) {
			System.out.println(value + " is a leap year");
		}
		else if(value%400 !=0) {
			System.out.println(value + " is a common year");
		}
		else {
			System.out.println(value +" is a leap year");
		}
	}

}
