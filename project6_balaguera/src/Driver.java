import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        String fileName = "xc.txt";
        Scanner userIn = new Scanner(System.in);

        System.out.println("Enter first team name");
        String teamOne = userIn.nextLine();

        System.out.println("Enter second team name");
        String teamTwo = userIn.nextLine();

        userIn.close();

        System.out.println(Score.calculateScore(Parse.read(fileName), teamOne, teamTwo));
    }
}
