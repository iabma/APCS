import java.util.Scanner;

/**
 * Calculates a dual meet score between two teams from a source file.
 * @author Ian Balaguera
 * @version 11.13.18
 */
public class Driver {
    public static void main(String[] args) throws Exception {
        String fileName = "xc.txt";
        Scanner userIn = new Scanner(System.in);

        System.out.print("First team: ");
        String teamOne = userIn.nextLine();

        System.out.print("Second team: ");
        String teamTwo = userIn.nextLine();

        userIn.close();

        Parse parsedFile = new Parse(fileName);

        DualMeet meet = new DualMeet(parsedFile.data(), teamOne, teamTwo);

        meet.calculateScore();

        System.out.println(meet);
    }
}
