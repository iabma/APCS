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

    private static String currentPlayer;

    /**
     * Prompts the two players for their desired chip colors and names for the match, then runs
     * the game until either one of the players reaches a win condition or the board fills up. In
     * either case, the loop is broken and endgame messages are printed.
     * @throws FileNotFoundException intro.txt is given to exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String[] color = new String[Game.NUM_PLAYERS];
        String[] name = new String[Game.NUM_PLAYERS];

        printIntro();

        gameSetup(name, color, input);

        Game play = new Game(color);

        // Endgame outputs
        if (playGame(play, name, color, input) == Event.WIN) {
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

    /*
    Prompts the two users for the colors that they want their chips to be and their respective
    names.
     */
    private static void gameSetup(String[] name, String[] color, Scanner input) {
        Arrays.fill(color, "");
        System.out.println("Possible colors: Black, Red, Green, Yellow, Blue, Cyan, White");
        for (int i = 0; i < Game.NUM_PLAYERS; i++) {
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
    }

    /*
    Alternates between players choosing the column in which they want to drop their chip until
    either one player wins or the board fills up and the game results in a tie.
     */
    private static Event playGame(Game game, String[] name, String[] color, Scanner input) {
        String column;
        boolean player = true;
        Event endGame = Event.CONTINUE;

        while (endGame == Event.CONTINUE) {
            currentPlayer = player ? color[0] + name[0] + ANSI_RESET :
                    color[1] + name[1] + ANSI_RESET;
            System.out.println(currentPlayer +" >>");
            column = input.nextLine();
            while (!Check.isValid(column).equals("valid")) {
                System.out.println(Check.isValid(column));
                column = input.nextLine();
            }

            endGame = game.addChip(Integer.parseInt(column) - 1, player);
            while (endGame == Event.COLUMNFULL) {
                System.out.println("Column is full! >>");
                column = input.nextLine();
                endGame = game.addChip(Integer.parseInt(column) - 1, player);
            }
            player = !player;
        }

        return endGame;
    }
}