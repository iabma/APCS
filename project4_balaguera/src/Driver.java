import java.util.Scanner;
import java.io.File;

/**
 * Reads data from a file named albumData.txt, stores the data into an Album
 * object, and prints out the result in Apple's required format for
 * submission to iTunes.
 *
 * @author Ian Balaguera
 * @version 10.12.18
 */
public class Driver {
    public static void main(String[] args) throws Exception {
        File albumData = new File("albumData.txt");
        Scanner textIn = new Scanner(albumData);
        int netTime = 0;
        double netPrice = 0.0;

        String artistName = textIn.next();
        textIn.skip(" - ");
        String albumName = textIn.nextLine();

        int numTracks = 0;
        while(textIn.hasNextLine()) {
            netTime += Integer.parseInt(textIn.next());
            netPrice += Double.parseDouble(textIn.next());
            numTracks++;
        }

        textIn.close();

        Album a = new Album(artistName, albumName, netTime, netPrice,
                numTracks);

        System.out.println(a);
    }
}
