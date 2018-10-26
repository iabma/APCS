import java.util.Scanner;

/**
 * A dice-rolling game.
 *
 * @author Ian Balaguera
 * @version 9.20.18
 */
public class GameDriver {
    public static void main(String[] args) {
        String nameOne;
        String nameTwo;

        System.out.println("Player one, what's your name?");
        Scanner console = new Scanner(System.in);
        nameOne = console.next();
        System.out.println("Player two, what's your name?");
        nameTwo = console.next();
        console.close();

        Player playerOne = new Player(nameOne);
        Player playerTwo = new Player(nameTwo);
        System.out.println("\n" + playerOne.getName() + " will be rolling against " + playerTwo.getName() + ".\n");

        playerOne.giveDie();
        System.out.println(playerOne.getName() + " has been given a die.");
        Pair pOneRoll = playerOne.roll();
        System.out.println(playerOne.getName() + " rolls a " + pOneRoll.one() + " and a " + pOneRoll.two() + "!\n");

        playerTwo.giveDie();
        System.out.println(playerTwo.getName() + " has been given a die.");
        Pair pTwoRoll = playerTwo.roll();
        System.out.println(playerTwo.getName() + " rolls a " +  pTwoRoll.one() + " and a " + pTwoRoll.two() + "!\n");

        if(pOneRoll.sum() > pTwoRoll.sum()) {
            System.out.println(playerOne.getName() + " wins!");
        } else if(pOneRoll == pTwoRoll) {
            System.out.println(playerOne.getName() + " and " + playerTwo.getName() + " tie!");
        } else {
            System.out.println(playerTwo.getName() + " wins!");
        }
    }
}