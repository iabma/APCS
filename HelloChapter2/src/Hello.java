import java.util.Scanner;

public class Hello {
	public static void main(String args[]) {
		//Prompts the user
		System.out.println("What's your name?");
		Scanner input = new Scanner(System.in);
		String name = input.next();
		input.close();
		
		//Displays a custom hello message
		System.out.println("Hello, " + name);
	}
}
