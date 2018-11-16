/**
 * Creates a Dual Meet object which can calculate the winner and the respective scores.
 * Tiebreakers are determined by the point values of the sixth place runners.
 * @author Ian Balaguera
 * @version 11.15.18
 */
public class DualMeet {
    private final int NUM_SCORED_RUNNERS = 5;
    private final int TIEBREAKING_PLACE = NUM_SCORED_RUNNERS + 1;
    private String[] data;
    private String[] teams;
    private int[] numPlayers;
    private int[] points;
    private int[] sixthPlacePoints;
    private int pointIndex;

    /**
     * Constructs a Dual Meet object using the necessary data and team names.
     * @param data the input in String array form
     */
    public DualMeet(String[] data) {
        this.data = data;
        teams = new String[2];
        numPlayers = new int[2];
        points = new int[2];
        sixthPlacePoints = new int[2];
        pointIndex = 1;
    }

    /**
     * Adds a team by index to the array of teams.
     * @param name  the school name
     * @param index the position of the school name in the team String array
     */
    public void addTeam(String name, int index) {
        teams[index] = name;
    }

    /**
     * Iterates through the data and finds the first six runner positions and their respective
     * point values.
     */
    public void calculateScore() {
        for (String line : data) {
            for (int j = 0; j < teams.length; j++) {
                if (line.contains(teams[j].toUpperCase())) {
                    numPlayers[j]++;
                    if (numPlayers[j] <= NUM_SCORED_RUNNERS) {
                        points[j] += pointIndex;
                    } else if (numPlayers[j] == TIEBREAKING_PLACE) {
                        sixthPlacePoints[j] = pointIndex;
                    }
                    pointIndex++;
                }
            }
        }
    }

    /**
     * Overrides the default toString() so the object can be directly printed.
     * Checks if there is a clear winner. If the two teams tied, the tiebreaker will be decided
     * by the sixth place runners.
     * @return ranked dual meet scores
     */
    @Override
    public String toString() {
        if (points[0] < points[1]) {
            return  String.format("%s - %s <-- WINNER\n%s - %s", teams[0].toUpperCase(),
                    points[0], teams[1].toUpperCase(), points[1]);
        } else if (points[0] == points[1]) {
            if (sixthPlacePoints[0] < sixthPlacePoints[1]) {
                return String.format("%s - %s <-- TIE WINNER (%d points)\n%s - %s",
                        teams[0].trim().toUpperCase(), points[0], sixthPlacePoints[0],
                        teams[1].toUpperCase(), points[1]);
            } else {
                return String.format("%s - %s <-- TIE WINNER (%d points)\n%s - %s",
                        teams[1].trim().toUpperCase(), points[1], sixthPlacePoints[1],
                        teams[0].toUpperCase(), points[0]);
            }
        } else {
            return  String.format("%s - %s <-- WINNER\n%s - %s", teams[1].toUpperCase(),
                    points[1], teams[0].toUpperCase(), points[0]);
        }
    }
}
