import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Driver class to play a round of connect four.
 * @author Ian Balaguera
 * @version 12.4.18
 */
public class Driver {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final int NUM_PLAYERS = 2;

    /**
     * Prompts the two players for their desired chip colors and names for the match, then runs
     * the game until either one of the players reaches a win condition or the board fills up. In
     * either case, the loop is broken and endgame messages are printed.
     * @param args
     * @throws FileNotFoundException intro.txt is given to exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String column;
        String currentPlayer = "";
        String[] color = new String[NUM_PLAYERS];
        String[] name = new String[NUM_PLAYERS];
        int endGame = 0;
        boolean player = true;

        printIntro();

        // Game setup
        Arrays.fill(color, "");
        System.out.println("Possible colors: Black, Red, Green, Yellow, Blue, Cyan, White");
        for (int i = 0; i < NUM_PLAYERS; i++) {
            System.out.println("Player " + (i + 1) + " chip color");
            color[i] = input.nextLine();
            while (Check.isValidColor(color[i]).equals("invalid")) {
                System.out.println("Enter a valid color");
                color[i] = input.nextLine();
            }
            color[i] = Check.isValidColor(color[i]);

            System.out.println("Player " + (i + 1) + " name");
            name[i] = input.nextLine();
            while (!Check.isValidName(name[i]).equals("valid")) {
                System.out.println(Check.isValidName(name[i]));
                name[i] = input.nextLine();
            }
        }

        Game play = new Game(color);

        // Game playing
        while (endGame == 0) {
            currentPlayer = player ? color[0] + name[0] + ANSI_RESET :
                    color[1] + name[1] + ANSI_RESET;
            System.out.println(currentPlayer +" >>");
            column = input.nextLine();
            while (!Check.isValid(column, play).equals("valid")) {
                System.out.println(Check.isValid(column, play));
                column = input.nextLine();
            }
            endGame = play.addChip(Integer.parseInt(column) - 1, player);
            player = !player;
        }

        // Endgame outputs
        if (endGame == 1) {
            System.out.println(currentPlayer + " wins.");
        } else {
            System.out.println("Game ends in a tie.");
        }
    }

    /*
    Prints the introduction to the game, with a little red twist the to big connect four sign
     */
    private static void printIntro() throws FileNotFoundException {
        Scanner info = new Scanner(new File("intro.txt"));
        info.useDelimiter("delim:");
        System.out.println(info.next());
        System.out.println(ANSI_RED + info.next() + ANSI_RESET);
        System.out.println(info.next());
    }
}