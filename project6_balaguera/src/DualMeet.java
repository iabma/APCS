/**
 * Creates a Dual Meet object which can calculate the winner and the respective scores.
 * @author Ian Balaguera
 * @version 11.14.18
 */
public class DualMeet {
    private String[] data;
    private String teamOne;
    private String teamTwo;
    private String winner;
    private String loser;
    private int pointIndex;
    private int pointsOne;
    private int pointsTwo;
    private int numPlayersOne;
    private int numPlayersTwo;
    private int teamOneSix;
    private int teamTwoSix;

    /**
     * Constructs a Dual Meet object using the necessary data and team names.
     * @param data the input in String array form
     * @param teamOne the first team's name
     * @param teamTwo the second team's name
     */
    public DualMeet(String[] data, String teamOne, String teamTwo) {
        this.data = data;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        pointIndex = 1;
        pointsOne = 0;
        pointsTwo = 0;
        numPlayersOne = 0;
        numPlayersTwo = 0;
        teamOneSix = 0;
        teamTwoSix = 0;
    }

    /**
     * Iterates through the data and finds the first five runner positions and compares them.
     * If they are tied, it goes to the sixth place runner for each team.
     */
    public void calculateScore() {
        for (int i = 0; i < data.length; i++) {
            if (data[i].substring(42, 67).trim().equalsIgnoreCase(teamOne)) {
                numPlayersOne++;
                if (numPlayersOne <= 5) {
                    pointsOne += pointIndex;
                } else if (numPlayersOne == 6) {
                    teamOneSix = pointIndex;
                }
                pointIndex++;
            } else if (data[i].substring(42, 67).trim().equalsIgnoreCase(teamTwo)) {
                numPlayersTwo++;
                if (numPlayersTwo <= 5) {
                    pointsTwo += pointIndex;
                } else if (numPlayersTwo == 6) {
                    teamTwoSix = pointIndex;
                }
                pointIndex++;
            }
        }

        if (pointsOne < pointsTwo) {
            winner = String.format("%s - %s <-- WINNER", teamOne.trim().toUpperCase(), pointsOne);
            loser = String.format("%s - %s", teamTwo.trim().toUpperCase(), pointsTwo);
        } else if (pointsOne == pointsTwo) {
            if (teamOneSix < teamTwoSix) {
                winner = String.format("%s - %s <-- WINNER", teamOne.trim().toUpperCase(), pointsOne);
                loser = String.format("%s - %s", teamTwo.trim().toUpperCase(), pointsTwo);
            } else {
                winner = String.format("%s - %s <-- WINNER", teamTwo.trim().toUpperCase(), pointsTwo);
                loser = String.format("%s - %s", teamOne.trim().toUpperCase(), pointsOne);
            }
        } else {
            winner = String.format("%s - %s <-- WINNER", teamTwo.trim().toUpperCase(), pointsTwo);
            loser = String.format("%s - %s", teamOne.trim().toUpperCase(), pointsOne);
        }
    }

    /**
     * Overrides the default toString() so the object can be directly printed.
     * @return ranked dual meet scores
     */
    @Override
    public String toString() {
        return String.format("%s\n%s", winner, loser);
    }
}
