import java.util.Arrays;

public class AutoCorrect {
    private static final String[] TEAM_NAMES = {"TABOR ACADEMY", "WESTMINSTER", "THAYER ACADEMY",
            "ROXBURY LATIN", "GROTON", "WORCESTER ACADEMY", "HOPKINS", "SUFFIELD ACADEMY",
            "MIDDLESEX", "NOBLES", "BERKSHIRE", "SALISBURY", "BB&N", "KENT",
            "THE GOVERNOR'S ACADEMY", "WILLISTON"};
    private static final int NKPL = 14;

    private static char[][] teamSeq = new char[TEAM_NAMES.length][25];
    private static char[] chars = {
            '`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', 127,
              9, 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[', ']',  92,
              7,   7, 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';',  39,  13,
             32,  32, 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/',  32,  32,
              7,   7,   7,   7, ' ', ' ', ' ', ' ', ' ',   7,   7,   7,   7,   7, };

    public static String check(String toCheck) {
        convertToSeq();

        char[] seq = new char[toCheck.length()];

        for (int i = 0; i < toCheck.length(); i++) {
            seq[i] = toCheck.toLowerCase().charAt(i);
        }

        String closest = "!not!";
        double smallest = 100;
        for (int i = 0; i < TEAM_NAMES.length; i++) {
            double meanOffset = 0;
            for (int j = 0; j < seq.length; j++) {
                int seqIndex = Arrays.toString(chars).indexOf(seq[j]);
                int teamSeqIndex = Arrays.toString(chars).indexOf(teamSeq[i][j]);
                double dist = Math.sqrt(
                        Math.pow((double)(seqIndex % NKPL - teamSeqIndex % NKPL),2) +
                        Math.pow((double)(seqIndex / NKPL - teamSeqIndex / NKPL),2));
                //System.out.print(dist + " ");
                meanOffset += Math.abs(dist);
            }
            meanOffset += Math.abs(TEAM_NAMES[i].length() - seq.length);
            //System.out.print(Math.abs(TEAM_NAMES[i].length() - seq.length) + " ");
            //System.out.println(meanOffset + " : " + TEAM_NAMES[i]);
            if (meanOffset < smallest) {
                smallest = meanOffset;
                //System.out.println(smallest);
                closest = TEAM_NAMES[i];
            }
        }

        return closest;
    }

    private static void convertToSeq() {
        for (int i = 0; i < TEAM_NAMES.length; i++) {
            for (int j = 0; j < TEAM_NAMES[i].length(); j++) {
                teamSeq[i][j] = TEAM_NAMES[i].toLowerCase().charAt(j);
            }
        }
    }
}
