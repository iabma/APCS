/**
 * An error-checking class containing one static method which uses parameters to ensure user
 * inputs are proper.
 *
 * @author Ian Balaguera
 * @version 1.15.19
 */
public class Check {
    /**
     * Provides a response String based off user input and Library-sourced parameters.
     * @param input user input
     * @param intArg integer limit if in use (i.e. not -1)
     * @param stringArg character blacklist or deciding factor between page navigating integer
     *                  prompts and length/number of pages input
     * @return either an error message or "::" if there are no errors.
     */
    public static String error(String input, int intArg, String stringArg) {
        if (intArg >= 0) {
            try {
                int choice = Integer.parseInt(input) - 1;
                if (stringArg.equals("id")) choice++;
                if (choice > intArg || choice < 0) {
                    return "not a valid choice::";
                }
            } catch (NumberFormatException ex) {
                return "not an integer::";
            }
        }

        if (!stringArg.equals("")) {
            for (char regex : stringArg.toCharArray()) {
                if (input.contains(String.valueOf(regex)))
                    return "character \"" + regex + "\" not allowed.";
            }
        }

        return "::";
    }
}