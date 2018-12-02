import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Game play = new Game();
        Scanner input = new Scanner(System.in);
        String playerOne = "p1";
        String playerTwo = "p2";
        String column;
        int endGame = 0;
        boolean player = true;

        while (endGame == 0) {
            String currentPlayer = player ? playerOne : playerTwo;
            System.out.print(currentPlayer + "'s move >> ");
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
            String winner = player ? playerTwo : playerOne;
            System.out.println(winner + " wins.");
        } else {
            System.out.println("Game ends in a tie.");
        }
    }
}