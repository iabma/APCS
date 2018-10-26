/**
 * @author Ian Balaguera
 * @version 9.20.18
 */
public class Player {
    private String name;
    private Die die;
    private Pair rolls;

    /**
     * Creates a new Player.
     * @param name Player name.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Creates a default Player.
     */
    public Player() {
        this("Default");
    }

    /**
     * Initializes the Die.
     */
    public void giveDie() {
        die = new Die();
    }

    /**
     * The player rolls the die twice.
     * @return A Pair containing the two roll values.
     */
    public Pair roll() {
        die.rollDie();
        rolls = new Pair(die.getLastCast(), die.rollDie());
        return rolls;
    }

    /**
     * Gets the player's name.
     * @return Name.
     */
    public String getName() {
        return name;
    }
}