import java.io.PrintStream;
import java.util.Arrays;

public class Game {
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLUMNS = 7;
    private static final int LAST_ROW = NUM_ROWS - 1;
    private static final int LAST_COLUMN = NUM_COLUMNS - 1;
    private static final int NUM_POSSIBLE_DIAGONALS = 6;
    private static final int WIN_LENGTH = 4;
    private static final String PLAYER_ONE_UNICODE = "\u232C";
    private static final String PLAYER_TWO_UNICODE = "\u2311";

    private String[][] board;
    private Board visualBoard;

    public Game() {
        board = new String[NUM_COLUMNS][NUM_ROWS];
        for (int i = 0; i < NUM_COLUMNS; i++) {
            Arrays.fill(board[i], " ");
        }
        visualBoard = new Board();
    }

    public boolean spaceInColumn(int column) {
        for (int i = 0; i < NUM_ROWS; i++) {
            if (board[column][i].equals(" ")) return true;
        }
        return false;
    }

    public int addChip(int column, boolean player) {
        String playerUnicode = player ? PLAYER_ONE_UNICODE : PLAYER_TWO_UNICODE;
        int row = LAST_ROW;

        for (int i = LAST_ROW; i >= 0; i--) {
            if (board[column][i].equals(" ")) {
                board[column][i] = playerUnicode;
                row = i;
                break;
            }
        }

        visualBoard.update(board[column], column);
        refresh(System.out);
        return checkCondition(player, column, row);
    }

    private int checkCondition(boolean player, int column, int row) {
        String playerUnicode = player ? PLAYER_ONE_UNICODE : PLAYER_TWO_UNICODE;
        if (row <= NUM_ROWS - WIN_LENGTH) {
            int numInColumn = 0;
            for (int i = 0; i < WIN_LENGTH; i++) {
                if (board[column][row + i].equals(playerUnicode)) {
                    numInColumn++;
                    if (numInColumn == WIN_LENGTH) return 1;
                } else {
                    numInColumn = 0;
                }
            }
        }
        int numInRow = 0;
        for (int i = 0; i < NUM_COLUMNS; i++) {
            if (board[i][LAST_ROW].equals(playerUnicode)) {
                numInRow++;
                if (numInRow == WIN_LENGTH) return 1;
            } else {
                numInRow = 0;
            }
        }

        if (checkDiagonal(playerUnicode) == 1) return 1;

        return checkTie();
    }

    private int checkTie() {
        for (String[] column : board) {
            if (column[0].equals(" ")) return 0;
        }
        return 2;
    }

    private int checkDiagonal(String playerUnicode) {
        int numInSequence = 0;

        for (int i = 0; i < NUM_POSSIBLE_DIAGONALS; i++) {
            if (i < 3) {
                for (int j = 0; j < i + WIN_LENGTH; j++) {
                    if (board[j][NUM_ROWS - WIN_LENGTH - i + j].equals(playerUnicode)) {
                        numInSequence++;
                        if (numInSequence == WIN_LENGTH) return 1;
                    } else {
                        numInSequence = 0;
                    }
                }
            } else {
                for (int j = i - 2; j < NUM_COLUMNS; j++) {
                    if (board[j][j - i + 2].equals(playerUnicode)) {
                        numInSequence++;
                        if (numInSequence == WIN_LENGTH) return 1;
                    } else {
                        numInSequence = 0;
                    }
                }
            }
        }

        for (int i = 0; i < NUM_POSSIBLE_DIAGONALS; i++) {
            if (i < 3) {
                for (int j = 0; j < i + WIN_LENGTH; j++) {
                    if (board[LAST_COLUMN - j][NUM_ROWS - WIN_LENGTH - i + j].equals(playerUnicode)) {
                        numInSequence++;
                        if (numInSequence == WIN_LENGTH) return 1;
                    } else {
                        numInSequence = 0;
                    }
                }
            } else {
                for (int j = i - 2; j < NUM_COLUMNS; j++) {
                    if (board[LAST_COLUMN - j][j - i + 2].equals(playerUnicode)) {
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

    private void refresh(PrintStream out) {
        out.println(this.toString());
    }

    @Override
    public String toString() {
        return visualBoard.toString();
    }
}