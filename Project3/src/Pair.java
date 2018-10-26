/**
 * @author Ian Balaguera
 * @version 9.20.18
 */
public class Pair {
    private int one;
    private int two;

    /**
     * Creates a new Pair.
     * @param one First integer.
     * @param two Second integer.
     */
    public Pair(int one, int two) {
        this.one = one;
        this.two = two;
    }

    /**
     * Returns the first integer in the pair.
     * @return The first int.
     */
    public int one() {
        return one;
    }

    /**
     * Returns the second integer in the pair.
     * @return The second int.
     */
    public int two() {
        return two;
    }

    /**
     * Returns the sum of the pair.
     * @return The int sum.
     */
    public int sum() {
        return one + two;
    }
}
