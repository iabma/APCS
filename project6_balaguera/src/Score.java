import java.util.Scanner;

public class Score {
    public static String calculateScore(String[] data, String teamOne, String teamTwo) {
        int pointIndex = 1;
        int pointsOne = 0;
        int pointsTwo = 0;
        int numPlayersOne = 0;
        int numPlayersTwo = 0;
        int teamOneSix = 0;
        int teamTwoSix = 0;

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
            return String.format("%s - %s\n%s - %s", teamOne.trim().toUpperCase(), pointsOne,
                    teamTwo.trim().toUpperCase(), pointsTwo);
        } else if (pointsOne == pointsTwo) {
            if (teamOneSix < teamTwoSix) return String.format("%s - %s\n%s - %s",
                    teamOne.trim().toUpperCase(), pointsOne, teamTwo.trim().toUpperCase(),
                    pointsTwo);
            else return String.format("%s - %s\n%s - %s", teamTwo.trim().toUpperCase(), pointsTwo,
                    teamOne.trim().toUpperCase(), pointsOne);
        } else {
            return String.format("%s - %s\n%s - %s", teamTwo.trim().toUpperCase(), pointsTwo,
                    teamOne.trim().toUpperCase(), pointsOne);
        }
    }
}
