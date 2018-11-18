import java.util.Arrays;

public class AutoCorrect {
    private static final String[] TEAM_NAMES = {"TABOR ACADEMY", "WESTMINSTER", "THAYER ACADEMY",
            "ROXBURY LATIN", "GROTON", "WORCESTER ACADEMY", "HOPKINS", "SUFFIELD ACADEMY",
            "MIDDLESEX", "NOBLES", "BERKSHIRE", "SALISBURY", "BB&N", "KENT",
            "THE GOVERNOR'S ACADEMY", "WILLISTON"};
    private static char[][] teamSeq = new char[TEAM_NAMES.length][25];
    private static char[] horizOrder = {'`','1','2','3','4','5','6','7','8','9','0','-','=','q'
        ,'w','e','r','t','y','u','i','o','p','[',']','a', 's','d','f','g','h','j','k','l',';',
        'z','x','c','v','b','n','m',',','.','/'};
    private static char[] vertOrder = {'1','q','a','z','2','w','s','x','3','e','d','c',' ',
            '4','r','f','v',' ','5','t','g','b',' ','6','y','h','n','7','u','j','m','8','i',
            'k',',','9','o','l','.','0','p',';','/','-','[','=',']'};

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
                double avgDist =
                        ((double) Arrays.toString(horizOrder).indexOf(seq[j]) -
                                (double) Arrays.toString(horizOrder).indexOf(teamSeq[i][j]) +
                                (double) Arrays.toString(vertOrder).indexOf(seq[j]) -
                                (double) Arrays.toString(vertOrder).indexOf(teamSeq[i][j]))/2;
                //System.out.print(avgDist + " ");
                meanOffset += Math.abs(avgDist);
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
