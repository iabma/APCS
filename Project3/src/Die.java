import java.util.Random;

/**
 * @author Ian Balaguera
 * @version 9.20.18
 */
public class Die {
    private int upFace;
    private int lastCast;

    /**
     * Creates a new Die.
     */
    public Die() {}

    /**
     * Rolls the die.
     * @return The number on the upward-pointing face.
     */
    public int rollDie() {
        Random cast = new Random();
        upFace = cast.nextInt(6) + 1;
        lastCast = upFace;
        return upFace;
    }

    /**
     * Returns the previous cast.
     * @return The previous cast.
     */
    public int getLastCast() {
        return lastCast;
    }
}