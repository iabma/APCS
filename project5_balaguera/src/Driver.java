import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("stringTransformations.txt"));

        while(in.hasNextLine()) {
            String transRules = in.nextLine();
            String inputLine = in.nextLine();
            for (int i = 0; i < (transRules.length() + 1) / 5 ; i++) {
                inputLine = inputLine.replaceFirst(String.valueOf(transRules.charAt(5 * i)),
                        transRules.substring(5 * i + 2, 5 * i + 4));
            }
            System.out.println(inputLine);
        }

        in.close();
    }
}