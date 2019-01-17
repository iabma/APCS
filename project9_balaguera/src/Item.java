/**
 * The superclass for any items which may be stored in the library.
 * Subclasses: Book, DVD
 *
 * @author Ian Balaguera
 * @version 1.15.19
 */
public class Item {
    private static long numItems = 0;

    private String description;
    private long ID;
    private boolean inStock;

    /**
     * Creates a new Item using the static numItems incremented by 1 as an ID, and the parameter
     * as a description.
     * @param description description of item
     */
    public Item(String description) {
        ID = numItems++;
        inStock = true;
        this.description = description;
    }

    /**
     * Changes status of "inStock" to false; Checks the item out.
     */
    public void checkOut() {
        inStock = false;
    }

    /**
     * Changes status of "inStock" to true; Returns the item.
     */
    public void returnIt() {
        inStock = true;
    }

    /**
     * Gets the item description.
     * @return item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the item ID.
     * @return item ID
     */
    public long getID() {
        return ID;
    }

    /**
     * Checks to see if the item is in stock.
     * @return true if yes, false if no
     */
    public boolean isInStock() {
        return inStock;
    }
}