import java.io.File;
import java.util.Scanner;

/**
 * Reads the input file and converts the necessary data into a String array.
 * @author Ian Balaguera
 * @version 11.13.18
 */
public class Parse {
    /**
     * Converts the inputted .txt file into a String array consisting only of the necessary data.
     * @param fileName the name of the input file.
     * @return the input file in String array form.
     * @throws Exception this is above my pay raise I don't want to write a try catch for this
     * project
     */
    public static String[] read(String fileName) throws Exception {
        Scanner input = new Scanner(new File(fileName));
        Scanner second = new Scanner(new File(fileName));
        skipIntro(input);

        int numLines = 0;
        while (input.hasNextLine()) {
            input.nextLine();
            numLines++;
        }

        String[] data = new String[numLines];
        skipIntro(second);
        for (int i = 0; i < numLines; i++) {
            data[i] = second.nextLine();
        }

        input.close();
        second.close();

        return data;
    }

    /**
     * Skips the unneeded first four lines of the input file.
     * @param input the input file scanner.
     */
    private static void skipIntro(Scanner input) {
        for (int i = 0; i < 4; i++) {
            input.nextLine();
        }
    }
}
