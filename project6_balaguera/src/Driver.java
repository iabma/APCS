import java.util.Scanner;

/**
 * Calculates a dual meet score between two teams from a source file.
 * @author Ian Balaguera
 * @version 11.14.18
 */
public class Driver {
    public static void main(String[] args) throws Exception {
        String fileName = "xc.txt";
        Scanner userIn = new Scanner(System.in);
        Parse parsedFile = new Parse(fileName);

        parsedFile.parse();

        DualMeet meet = new DualMeet(parsedFile.data());

        String firstTeamName = "";
        for (int i = 0; i < 2; i++) {
            String team = "";
            boolean valid = false;
            while (!valid) {
                System.out.print("Team: ");
                team = userIn.nextLine();
                team = AutoCorrect.check(team);
                valid = !team.equals("!not!");
                if (team.trim().equalsIgnoreCase(firstTeamName)) {
                    System.out.println("Team name has already been inputted.");
                    valid = false;
                }
                if (!valid) System.out.println("Invalid team name.");
            }
            firstTeamName = team;
            System.out.println(team);
            meet.addTeam(team, i);
        }

        userIn.close();

        meet.calculateScore();
        System.out.println(meet);
    }
}
