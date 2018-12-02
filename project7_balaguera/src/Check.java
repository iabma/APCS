public class Check {
    public static boolean isValidInt(String input) {
        if (!isInt(input)) return false;
        int intVersion = Integer.parseInt(input);
        if (intVersion <= 7 && intVersion >= 1) {
            return true;
        }
        return false;
    }

    public static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean hasSpace(String input, Game game) {
        if (!isValidInt(input)) return false;
        int intVersion = Integer.parseInt(input);
        return game.spaceInColumn(intVersion - 1);
    }

    public static boolean isValid(String input, Game game) {
        return hasSpace(input, game);
    }
}