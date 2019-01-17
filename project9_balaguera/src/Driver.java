import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The primary class for this program. It creates a new Library and loops while the Library is
 * running. In every loop, either the current page or prompt is printed, then the user is
 * prompted to input an appropriate response. An error check is run, ensuring that the input is
 * acceptable for the specific situation.
 *
 * @author Ian Balaguera
 * @version 1.15.19
 */
public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        Library library = new Library();
        Scanner in = new Scanner(System.in);
        String input = null,
                status = "::";

        System.out.println(library.update(input));
        while (library.isRunning()) {
            do {
                System.out.println(status);
                input = in.nextLine();
                status = Check.error(input, library.getIntArg(), library.getStringArg());
            } while (!status.equals("::"));
            String libraryOutput = library.update(input);
            if (libraryOutput.contains(":::: LIBRARY BOOKS ::::")) {
                PrintWriter out = new PrintWriter(new File(input + ".txt"));
                out.print(libraryOutput);
                out.close();
                System.out.println(library.update(null));
            } else {
                System.out.println(libraryOutput);
            }
        }
    }
}