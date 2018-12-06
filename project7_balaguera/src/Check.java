/**
 * Error checking class which includes methods for specific possible errors, such as an integer
 * being out of column bounds, or the input not even being an integer.
 * @author Ian Balaguera
 * @version 12.4.18
 */
public class Check {
    private static final String[] COLORS = {"black", "red", "green", "yellow", "blue",
            "purple", "cyan", "white"};
    private static final String[] ANSI_COLORS = {"\u001B[30m", "\u001B[31m",
            "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B" + "[35m", "\u001B[36m",
            "\u001B[37m"};

    /**
     * Checks if inputted color is within the preset.
     * @param input user input through the console
     * @return "invalid" if not present, desired ANSI color escape code if present
     */
    public static String isValidColor(String input) {
        int index = -1;
        for (int i = 0; i < COLORS.length; i++) {
            if (input.equalsIgnoreCase(COLORS[i])) {
                index = i;
            }
        }
        return index == -1 ? "invalid" : ANSI_COLORS[index];
    }

    /**
     * Checks if the inputted name is not simply a bunch of spaces.
     * @param input user input through the console
     * @return "valid" if has other characters, error message if only spaces
     */
    public static String isValidName(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ') return "valid";
        }
        return "Enter a valid name";
    }

    /**
     * Checks if the inputted value is an integer within 1 and 7 inclusive.
     * @param input user input through the console
     * @return "valid" if valid, error message if otherwise
     */
    public static String isValidInt(String input) {
        if (!isInt(input).equals("valid")) return isInt(input);
        int intVersion = Integer.parseInt(input);
        if (intVersion <= 7 && intVersion >= 1) {
            return "valid";
        }
        return "Not a valid column! >>";
    }

    /**
     * Checks if the inputted value is an integer.
     * @param input user input through the console
     * @return "valid" if integer, error message if otherwise
     */
    public static String isInt(String input) {
        try {
            Integer.parseInt(input);
            return "valid";
        } catch (NumberFormatException ex) {
            return "Not an integer! >>";
        }
    }

    /**
     * Checks if the given Game instance has any space in the column specified by the user.
     * @param input user input through the console
     * @param game Game instance
     * @return "valid" if space remains, error message if otherwise
     */
    public static String hasSpace(String input, Game game) {
        if (!isValidInt(input).equals("valid")) return isValidInt(input);
        int intVersion = Integer.parseInt(input);
        return game.spaceInColumn(intVersion - 1);
    }

    /**
     * Checks if all parameters for a proper input are true
     * @param input user input through the console
     * @param game Game instance
     * @return "valid" if valid input, error message if otherwise
     */
    public static String isValid(String input, Game game) {
        return hasSpace(input, game);
    }
}