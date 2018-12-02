import java.util.Arrays;

public class Board {
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLUMNS = 7;

    private String[][] board;

    public Board() {
        board = new String[NUM_COLUMNS][NUM_ROWS];
        for (int i = 0; i < NUM_COLUMNS; i++) {
            Arrays.fill(board[i], " ");
        }
    }

    public void update(String[] column, int columnIndex) {
        board[columnIndex] = Arrays.copyOf(column, column.length);
    }

    @Override
    public String toString() {
        String printBoard = "";
        for (int i = 0; i < NUM_ROWS; i++) {
            printBoard += "|";
            for (int j = 0; j < NUM_COLUMNS; j++) {
                printBoard += " " + board[j][i] + " |";
            }
            printBoard += "\n";
        }
        printBoard += "-----------------------------\n";
        printBoard += "| 1 | 2 | 3 | 4 | 5 | 6 | 7 |\n";
        printBoard += "-----------------------------\n";
        return printBoard;
    }
}