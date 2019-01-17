/**
 * A subclass of Item specialized in DVD-like methods.
 *
 * @author Ian Balaguera
 * @version 1.15.19
 */
public class DVD extends Item {
    private String title;
    private String origin;
    private int length;

    /**
     * Constructs a DVD object using the given parameters.
     * @param title title of the DVD
     * @param origin country of origin
     * @param length length of footage
     * @param description description of dvd
     */
    public DVD(String title, String origin, int length, String description) {
        super(description);
        this.title = title;
        this.origin = origin;
        this.length = length;
    }

    /**
     * Gets the DVD title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the DVD country of origin.
     * @return origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Gets the DVD length.
     * @return length
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns a user-friendly set of information about the DVD.
     * @return DVD information
     */
    @Override
    public String toString() {
        return getID() + " - " + (isInStock() ? "AVAILABLE" : "CHECKED OUT") + "\n｜\ttitle: " +
                title + "\n｜\torigin: " + origin + "\n｜\tnumber of " + "pages: " + length +
                " minutes\n::";
    }
}