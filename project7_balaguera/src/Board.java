import java.util.Arrays;

/**
 * Class which makes Board objects that can be used to visualize the board, thus making the game
 * easier to play and more intuitive.
 * @author Ian Balaguera
 * @version 12.4.18
 */
public class Board {
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLUMNS = 7;

    private String[][] board;

    /**
     * Initializes board as an empty (but not null) two dimensional array.
     */
    public Board() {
        board = new String[NUM_COLUMNS][NUM_ROWS];
        for (int i = 0; i < NUM_COLUMNS; i++) {
            Arrays.fill(board[i], " ");
        }
    }

    /**
     * Copies the given column array into the board array.
     * @param column column to update
     * @param columnIndex index of column to update
     */
    public void update(String[] column, int columnIndex) {
        board[columnIndex] = Arrays.copyOf(column, column.length);
    }

    /**
     * Iterates through every row and prints out the respective value, thus creating a connect
     * four-esque UI in the console.
     * @return visual version of board array
     */
    @Override
    public String toString() {
        StringBuilder print = new StringBuilder("\n");
        for (int i = 0; i < NUM_ROWS; i++) {
            print.append("|");
            for (int j = 0; j < NUM_COLUMNS; j++) {
                print.append(" " + board[j][i] + " |");
            }
            print.append("\n");
        }
        print.append("-----------------------------\n|");
        for (int i = 0; i < NUM_COLUMNS; i++) {
            print.append(" " + (i + 1) + " |");
        }
        print.append("\n-----------------------------\n");
        return print.toString();
    }
}