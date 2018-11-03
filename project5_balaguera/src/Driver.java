import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * This program will convert an encoded message into a legible output in the console using given
 * transformations.
 * @author Ian Balaguera
 * @version 10.31.18
 */
public class Driver {
    /**
     * This function finds the first instance of toFind in the inputted word and replaces in with
     * toReplace.
     * @param word The desired word to modify.
     * @param toFind The string of characters which will be replaced.
     * @param toReplace The string of characters which will replace toFind.
     * @return the modified word, unless the desired substring is not in the inputted word, in
     * which case it simply returns the inputted word.
     */
    public static String convert(String word, String toFind, String toReplace) {
        String modifiedWord = word.substring(0, word.indexOf(toFind)) + toReplace +
                word.substring(word.indexOf(toFind) - 1 + toReplace.length());
        return !word.contains(toFind) ? word : modifiedWord;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("stringTransformations.txt"));

        while(in.hasNextLine()) {
            String transRules = in.nextLine();
            String inputLine = in.nextLine();
            int numTransformations = (transRules.length() + 1) / 5;

            for (int i = 0; i < numTransformations; i++) {
                String charToBeReplaced = String.valueOf(transRules.charAt(5 * i));
                String transformation = transRules.substring(5 * i + 2, 5 * i + 4);

                inputLine = convert(inputLine, charToBeReplaced, transformation);
            }

            System.out.println(inputLine);
        }

        in.close();
    }
}