import java.io.File;
import java.util.Scanner;

/**
 * Reads the input file and converts the necessary data into a String array.
 * @author Ian Balaguera
 * @version 11.13.18
 */
public class Parse {
    private String[] data;

    /**
     * Converts the inputted .txt file into a String array consisting only of the necessary data.
     * @param fileName the xc.txt file, for this specific project
     * @throws Exception if the requested file does not exist
     */
    public Parse (String fileName) throws Exception {
        Scanner input = new Scanner(new File(fileName));
        Scanner second = new Scanner(new File(fileName));
        skipIntro(input);

        int numLines = 0;
        while (input.hasNextLine()) {
            input.nextLine();
            numLines++;
        }

        data = new String[numLines];
        skipIntro(second);
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

    private static void skipIntro(Scanner input) {
        for (int i = 0; i < 4; i++) {
            input.nextLine();
        }
    }
}
