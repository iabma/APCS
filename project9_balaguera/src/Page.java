/**
 * Used to store useful information about a page in a Library interface as well as create a
 * visual for such information.
 *
 * @author Ian Balaguera
 * @version 1.15.19
 */
public class Page {
    private String name;
    private String[] options;

    /**
     * Constructs a new Page using the given name and page options.
     * @param name page name
     * @param options options presented to the user
     */
    public Page(String name, String[] options) {
        this.name = name;
        this.options = options;
    }

    /**
     * Gets the page options.
     * @return page options
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Gets the page name.
     * @return page name
     */
    public String getName() {
        return name;
    }

    /**
     * Creates a user-friendly visual of the page.
     * @return page visual
     */
    @Override
    public String toString() {
        String str = ":::: " + name.toUpperCase() + " ::::";
        for (int i = 0; i < options.length; i++) {
            str += "\n\t" + (i + 1) + ": " + options[i];
        }
        return str;
    }
}