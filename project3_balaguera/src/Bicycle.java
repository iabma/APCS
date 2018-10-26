/**
 * Creates and modifies a Bicycle.
 *
 * @author Ian Balaguera
 * @version 10.2.18
 */
public class Bicycle {

    /* VARIABLE DECLARATION */

    // CONSTANTS
    private static final String[] ANSI = {"\u001B[0m", "\u001B[30m",
            "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B" +
            "[35m", "\u001B[36m", "\u001B[37m"};
    private static final String[] ANSIBack = {"\u001B[0m", "\u001B[40m",
            "\u001B[41m", "\u001B[42m", "\u001B[43m", "\u001B[44m", "\u001B" +
            "[45m", "\u001B[46m", "\u001B[47m"};
    private static final String[] Colors = {"reset", "black", "red", "green",
            "yellow", "blue", "purple", "cyan", "white"};

    private static final double mpstokmph = 3.6;
    private static final double bikeMass = 6.7;

    // VARIABLES
    private String color;
    private double velocity;
    private double acceleration;
    private double defaultAcc;
    private double distance;
    private double altitude;
    private double lastAlt;
    private double detail;
    private int fractionalAlt;
    private int duration;

    /* CONSTRUCTORS */

    /**
     * Creates a Bicycle object with the given parameters.
     *
     * @param color Color of the bicycle.
     * @param effort Amount of effort used when pedaling.
     * @param detail Level of detail in the route.
     * @param duration Duration of the bike ride.
     */
    public Bicycle(String color, String effort, int detail, int duration) {
        // PARAMETRIC VARIABLES
        int index = 8;
        for (int i = 0; i < Colors.length; i++) {
            if (color.equalsIgnoreCase(Colors[i])) {
                index = i;
            }
        }
        this.color = ANSI[index] + ANSIBack[index];
        System.out.println("Color: " + Colors[index]);

        this.detail = detail;
        System.out.println("Level of Detail: " + detail);

        if (effort.equalsIgnoreCase("much")) {
            defaultAcc = 3 / bikeMass / detail;
            System.out.println("Effort: Much");
        } else if (effort.equalsIgnoreCase("some")) {
            defaultAcc = 2.5 / bikeMass / detail;
            System.out.println("Effort: some");
        } else {
            defaultAcc = 2 / bikeMass / detail;
            if (effort.equalsIgnoreCase("little")) {
                System.out.println("Effort: Little");
            } else {
                System.out.println("Effort: As little as you put into your " +
                        "improper input.");
            }
        }

        this.duration = duration;
        System.out.println("Duration: " + duration + " seconds");

        // DEFAULT VARIABLES
        velocity = 0;
        acceleration = 0;
        distance = 0;
        altitude = 0;
        lastAlt = 100;
    }

    /* PUBLIC FUNCTIONS */

    /**
     * "Rides" the bicycle for one second.
     *
     * @param θ The angle of incline/decline.
     */
    public void ride(double θ) {
        double counterAcc = (9.8 / detail) * Math.sin((θ / 360) * 2 * Math.PI);
        altitude += 20 * Math.sin((θ / 360) * 2 * Math.PI);

        for (int n = 0; n < detail; n++) {
            acceleration = defaultAcc - counterAcc;
            velocity += acceleration;

            if (velocity >= 13.0) {
                velocity = 13.0;
            } else if (velocity < 0) {
                velocity = 0;
            }

            fractionalAlt = (int) (lastAlt + ((altitude + 100 - lastAlt)
                    * Math.sin(n / detail * Math.PI / 2)));

            set();

            distance += velocity / detail;

            try {
                Thread.sleep((int)(1000 / detail));
            } catch (InterruptedException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        lastAlt = altitude + 100;
    }

    /**
     * Returns the bicycle ride's statistics.
     *
     * @return Average velocity (km/h), distance (m), change in altitude (m).
     */
    public double[] getStats() {
        double avgVel = distance / duration;
        double[] stats = {avgVel * mpstokmph, distance,
                Math.abs(altitude)};
        for (int i = 0; i < stats.length; i++) {
            stats[i] = (double) Math.round(stats[i] * 100) / 100;
        }
        return stats;
    }

    /* PRIVATE FUNCTIONS */

    private void set() {
        System.out.print("\n");

        for (int i = 0; i < fractionalAlt; i++) {
            System.out.print(color + "|" + ANSI[0]);
        }

        System.out.print(" : " + (double)Math.round(velocity * mpstokmph
                * 100) / 100 + " km/h");
    }
}