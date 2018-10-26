import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HelloOutput {
	public static void main(String args[]) throws FileNotFoundException {
		//Creates a new file and writes onto it
		PrintWriter printer = new PrintWriter("hello.txt");
		printer.println("Hello, File Output!");
		printer.close();
	}
}
