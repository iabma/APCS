/**
 * A subclass of Item specialized in Book-like methods.
 *
 * @author Ian Balaguera
 * @version 1.15.19
 */
public class Book extends Item {
    private String title;
    private String author;
    private int numPages;

    /**
     * Constructs a Book object using the given parameters.
     * @param title title of the book
     * @param author author
     * @param numPages number of pages
     * @param description description of book
     */
    public Book(String title, String author, int numPages, String description) {
        super(description);
        this.title = title;
        this.author = author;
        this.numPages = numPages;
    }

    /**
     * Gets the book title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the book author.
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the number of pages.
     * @return number of pages
     */
    public int getNumPages() {
        return numPages;
    }

    /**
     * Returns a user-friendly set of information about the Book.
     * @return Book information
     */
    @Override
    public String toString() {
        return getID() + " - " + (isInStock() ? "AVAILABLE" : "CHECKED OUT") + "\n\ttitle: " + title
                + "\n\tauthor: " + author + "\n\tnumber of pages: " + numPages + " pages\n::";
    }
}