import java.util.Scanner;

/**
 * Calculates a dual meet score between two teams from a source file.
 * @author Ian Balaguera
 * @version 11.14.18
 */
public class Driver {
    public static void main(String[] args) throws Exception {
        String[] teams = {"TABOR ACADEMY", "WESTMINSTER", "THAYER ACADEMY", "ROXBURY LATIN",
                "GROTON", "WORCESTER ACADEMY", "HOPKINS", "SUFFIELD ACADEMY", "MIDDLESEX",
                "NOBLES", "BERKSHIRE", "SALISBURY", "BB&N", "KENT", "THE GOVERNOR'S ACADEMY",};
        String fileName = "xc.txt";
        Scanner userIn = new Scanner(System.in);

        String teamOne = "";
        boolean realTeamOne = false;
        while (!realTeamOne) {
            System.out.print("First team: ");
            teamOne = userIn.nextLine();
            for (int i = 0; i < teams.length; i++) {
                if (teamOne.trim().equalsIgnoreCase(teams[i])) {
                    realTeamOne = true;
                }
            }
            if (!realTeamOne) System.out.println("Invalid team name.");
        }

        String teamTwo = "";
        boolean realTeamTwo = false;
        while (!realTeamTwo) {
            System.out.print("Second team: ");
            teamTwo = userIn.nextLine();
            for (int i = 0; i < teams.length; i++) {
                if (teamTwo.trim().equalsIgnoreCase(teams[i])) {
                    realTeamTwo = true;
                }
            }
            if (!realTeamTwo) System.out.println("Invalid team name.");
        }

        userIn.close();

        Parse parsedFile = new Parse(fileName);

        DualMeet meet = new DualMeet(parsedFile.data(), teamOne, teamTwo);

        meet.calculateScore();

        System.out.println(meet);
    }
}
