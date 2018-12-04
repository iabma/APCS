import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
    private static final int NUM_PLAYERS = 2;
    private static final String[] ANSI_COLORS = {"\u001B[30m", "\u001B[31m",
            "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B" + "[35m", "\u001B[36m",
            "\u001B[37m"};
    private static final String[] COLORS = {"black", "red", "green", "yellow", "blue",
            "purple", "cyan", "white"};

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String column;
        String currentPlayer = "";
        String[] color = new String[NUM_PLAYERS];
        String[] name = new String[NUM_PLAYERS];
        int endGame = 0;
        boolean player = true;

        printIntro();

        Arrays.fill(color, "");
        System.out.println("Possible colors: Black, Red, Green, Yellow, Blue, Cyan, White");
        for (int i = 0; i < NUM_PLAYERS; i++) {
            System.out.print("Player " + (i + 1) + " chip color: ");
            color[i] = input.next();
            while (contains(color[i]) == -1) {
                System.out.print("Enter a valid color: ");
                color[i] = input.next();
            }
            color[i] = ANSI_COLORS[contains(color[i])];

            System.out.print("Player " + (i + 1) + " name: ");
            name[i] = input.next();
        }

        Game play = new Game(color);

        while (endGame == 0) {
            currentPlayer = player ? name[0] : name[1];
            System.out.print(currentPlayer + " >> ");
            column = input.next();
            while (!Check.isValid(column, play)) {
                if (!Check.hasSpace(column, play)) {
                    if (!Check.isValidInt(column)) {
                        if (!Check.isInt(column)) {
                            System.out.print("Not an integer! >> ");
                        } else {
                            System.out.print("Not a valid column! >> ");
                        }
                    } else {
                        System.out.print("No more space in that row! >> ");
                    }
                }
                column = input.next();
            }
            endGame = play.addChip(Integer.parseInt(column) - 1, player);
            player = !player;
        }

        if (endGame == 1) {
            System.out.println(currentPlayer + " wins.");
        } else {
            System.out.println("Game ends in a tie.");
        }
    }

    private static void printIntro() throws FileNotFoundException {
        Scanner info = new Scanner(new File("intro.txt"));
        info.useDelimiter("Delim");
        System.out.println(info.next());
    }

    private static int contains(String input) {
        int index = -1;
        for (int i = 0; i < COLORS.length; i++) {
            if (input.equalsIgnoreCase(COLORS[i])) {
                index = i;
            }
        }
        return index;
    }
}