import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class RandomNumberFile {
	public static void main(String args[]) throws FileNotFoundException, InterruptedException {
		//Filename inputted by the user
		System.out.println("What will you like to name this file?");
		Scanner input = new Scanner(System.in);
		String name = input.next(); 
		input.close();
		
		//Creates a new file and prepares to write onto it
		if(name.contains(".txt")==false) {
			name = name + ".txt";
		}
		PrintWriter printer  = new PrintWriter(name);
		
		//Prints a string of 10 integers between 50-100
		System.out.println("Creating File...");
		Random gen = new Random();
		for(int i=0; i<10; i++) {
			printer.println(5+gen.nextInt(21));
			Thread.sleep(75);
		}
		
		//Ends file-writing task and informs the user of the new file
		printer.close();
		System.out.print("File Created.");
		
		//Tells the user where to find the file
		System.out.println(" You can find the file at Users/IB8/eclipse-workspace/HelloChapter2/" + name);
	}
}