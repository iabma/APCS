import java.io.File;
import java.util.Scanner;

public class Parse {
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

    private static void skipIntro(Scanner input) {
        for (int i = 0; i < 4; i++) {
            input.nextLine();
        }
    }
}
