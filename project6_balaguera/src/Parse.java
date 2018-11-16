import java.io.File;
import java.util.Scanner;

/**
 * Reads the input file and converts the necessary data into a String array.
 * @author Ian Balaguera
 * @version 11.13.18
 */
public class Parse {
    private final int INTRO_LENGTH = 4;
    private String[] data;
    private Scanner input;
    private Scanner second;

    /**
     * Sets the two necessary scanners to the file name.
     * @param fileName the xc.txt file, for this specific project
     * @throws Exception if the requested file does not exist
     */
    public Parse (String fileName) throws Exception {
        input = new Scanner(new File(fileName));
        second = new Scanner(new File(fileName));
    }

    /**
     * Converts the inputted .txt file into a String array consisting only of the necessary data.
     */
    public void parse() {
        skip(input, INTRO_LENGTH);

        int numLines = 0;
        while (input.hasNextLine()) {
            input.nextLine();
            numLines++;
        }

        data = new String[numLines];

        skip(second, INTRO_LENGTH);
        for (int i = 0; i < numLines; i++) {
            data[i] = second.nextLine();
        }

        input.close();
        second.close();
    }

    /**
     * Provides the parsed data.
     * @return data
     */
    public String[] data() {
        return data;
    }

    private void skip(Scanner input, int numLines) {
        for (int i = 0; i < numLines; i++) {
            input.nextLine();
        }
    }
}
