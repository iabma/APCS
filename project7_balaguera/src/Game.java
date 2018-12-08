import java.io.PrintStream;
import java.util.Arrays;

/**
 * Main class used as the template for every instance of a connect four game to be created.
 * Inputs colors in the constructor, adds a chip with for a given player and determines if that
 * move results in no endgame situation, a win, or a tie.
 * @author Ian Balaguera
 * @version 12.4.18
 */
public class Game {
    private static final String PLAYER_ONE_UNICODE = "\u232C";
    private static final String PLAYER_TWO_UNICODE = "\u2311";
    private static final String ANSI_RESET = "\u001B[0m";
    public static final int NUM_PLAYERS = 2;
    public static final int NUM_COLUMNS = 7;
    private static final int NUM_ROWS = 6;
    private static final int LAST_ROW = NUM_ROWS - 1; // The index of the final row
    private static final int LAST_COLUMN = NUM_COLUMNS - 1; // The index of the final column
    // The number of parallel diagonal paths that could potentially contain a winning sequence.
    private static final int NUM_POSSIBLE_DIAGONALS = 6;
    // Number of diagonal lines starting from the side as opposed to the top.
    private static final int NUM_SIDE_DIAGONALS = 3;
    /*
    Offset to make i = 3 the column index at which the diagonal lines starting on the top begin.
    Since the first 3 diagonal lines start from the side, the next 3 start from the top. However,
    since the final "side" diagonal actually begins in the corner, the first "top" diagonal must
    begin one unit central of the corner. Therefore, instead of this number being 3 (to cancel
    out the starting "top" diagonal being i = 3), it is 2.
    */
    private static final int FIRST_COLUMN_OFFSET = 2;
    private static final int WIN_LENGTH = 4;

    private String[][] board;
    private String[] color; // The two colors chosen by the respective players
    private Board visualBoard; // The Board object which is used to visualize the board array

    /**
     * Constructs a new game object and declares, initializes, and prints out the first instance
     * of the board.
     * @param color the two ANSI color escape codes for both player chips.
     */
    public Game(String[] color) {
        this.color = color;
        board = new String[NUM_COLUMNS][NUM_ROWS];
        for (int i = 0; i < NUM_COLUMNS; i++) {
            Arrays.fill(board[i], " ");
        }
        visualBoard = new Board();
        System.out.println("\n" + visualBoard);
    }

    /**
     * The primary method of this class. Adds a chip in the desired column for whichever player
     * is up.
     * @param column desired column
     * @param player player whose chip will be added
     * @return 0 for no endgame, 1 for win, 2 for tie
     */
    public int addChip(int column, boolean player) {
        String playerChip = player ? color[0] + PLAYER_ONE_UNICODE + ANSI_RESET :
                color[1] + PLAYER_TWO_UNICODE + ANSI_RESET;
        int row = LAST_ROW;

        for (int i = LAST_ROW; i >= 0; i--) {
            if (board[column][i].equals(" ")) {
                board[column][i] = playerChip;
                row = i;
                break;
            }
        }

        visualBoard.update(board[column], column);
        refresh(System.out);
        return checkCondition(player, column, row);
    }

    /**
     * Checks if there is any space left in the desired column to which a chip will be added.
     * @param column desired column
     * @return "valid" if space remains, error message if not
     */
    public String spaceInColumn(int column) {
        for (int i = 0; i < NUM_ROWS; i++) {
            if (board[column][i].equals(" ")) return "valid";
        }
        return "Column is filled up! >>";
    }

    /*
    First checks if the player has four consecutive chips in a column. Next, checks every row
    and looks for four consecutive chips of the given player.
     */
    private int checkCondition(boolean player, int column, int row) {
        String playerChip = player ? color[0] + PLAYER_ONE_UNICODE + ANSI_RESET :
                color[1] + PLAYER_TWO_UNICODE + ANSI_RESET;

        if (checkColumn(playerChip, column, row) == 1) return 1;

        if (checkRow(playerChip) == 1) return 1;

        if (checkDiagonal(playerChip) == 1) return 1;

        return checkTie();
    }

    /*
    If the top row has been filled up, we can infer that the board has been filled up, and thus
    the method will return a 2, which indicates a tie. Otherwise, it will return a 0.
     */
    private int checkTie() {
        for (String[] column : board) {
            if (column[0].equals(" ")) return 0;
        }
        return 2;
    }

    /*
    If the chip is high enough in the column, the chips below it will be checked to see if they
    are all the same as the one just placed.
     */
    private int checkColumn(String playerChip, int column, int row) {
        if (row <= NUM_ROWS - WIN_LENGTH) {
            int numInColumn = 0;
            for (int i = 0; i < WIN_LENGTH; i++) {
                if (board[column][row + i].equals(playerChip)) {
                    numInColumn++;
                    if (numInColumn == WIN_LENGTH) return 1;
                } else {
                    numInColumn = 0;
                }
            }
        }
        return 0;
    }

    /*
    Checks the row in which the chip has just been placed for WIN_LENGTH consecutive chips
     */
    private int checkRow(String playerChip) {
        for (int i = 0; i < NUM_ROWS; i++) {
            int numInRow = 0;
            for (int j = 0; j < NUM_COLUMNS; j++) {
                if (board[j][LAST_ROW - i].equals(playerChip)) {
                    numInRow++;
                    if (numInRow == WIN_LENGTH) return 1;
                } else {
                    numInRow = 0;
                }
            }
        }
        return 0;
    }

    /*
    Iterates through the 12 possible diagonal paths (6 in each direction), checking if the given
    playerChip can be consecutively found four times. If so, the method returns 1. Otherwise, it
    returns 0.
     */
    private int checkDiagonal(String playerChip) {
        int numInSequence;

        // Upper left to lower right
        for (int i = 0; i < NUM_POSSIBLE_DIAGONALS; i++) {
            numInSequence = 0;
            if (i < NUM_SIDE_DIAGONALS) {
                for (int j = 0; j < i + WIN_LENGTH; j++) {
                    if (board[j][NUM_ROWS - WIN_LENGTH - i + j].equals(playerChip)) {
                        numInSequence++;
                        if (numInSequence == WIN_LENGTH) return 1;
                    } else {
                        numInSequence = 0;
                    }
                }
            } else {
                for (int j = i - FIRST_COLUMN_OFFSET; j < NUM_COLUMNS; j++) {
                    if (board[j][j - i + FIRST_COLUMN_OFFSET].equals(playerChip)) {
                        numInSequence++;
                        if (numInSequence == WIN_LENGTH) return 1;
                    } else {
                        numInSequence = 0;
                    }
                }
            }
        }

        // Lower left to upper right
        for (int i = 0; i < NUM_POSSIBLE_DIAGONALS; i++) {
            numInSequence = 0;
            if (i < NUM_SIDE_DIAGONALS) {
                for (int j = 0; j < i + WIN_LENGTH; j++) {
                    if (board[LAST_COLUMN - j][NUM_ROWS - WIN_LENGTH - i + j].equals(playerChip)) {
                        numInSequence++;
                        if (numInSequence == WIN_LENGTH) return 1;
                    } else {
                        numInSequence = 0;
                    }
                }
            } else {
                for (int j = i - FIRST_COLUMN_OFFSET; j < NUM_COLUMNS; j++) {
                    if (board[LAST_COLUMN - j][j - i + FIRST_COLUMN_OFFSET].equals(playerChip)) {
                        numInSequence++;
                        if (numInSequence == WIN_LENGTH) return 1;
                    } else {
                        numInSequence = 0;
                    }
                }
            }
        }

        return 0;
    }

    /*
    Updates the console view with the most recent board.
     */
    private void refresh(PrintStream out) {
        out.println(this.toString());
    }

    /**
     * Returns the board.
     * @return Board object
     */
    @Override
    public String toString() {
        return visualBoard.toString();
    }
}