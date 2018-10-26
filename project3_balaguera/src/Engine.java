import java.util.Scanner;
import java.util.Random;

/**
 * Given the length of the ride, the amount of effort used, the route color,
 * and the route detail, this program will provide a graphic of a bicycle
 * ride along with its statistics.
 *
 * @author Ian Balaguera
 * @version 10.2.18
 */
public class Engine {
    public static void main(String[] args) {

        /* VARIABLE DECLARATION */

        int duration = 0;
        int detail = 1;
        String color;
        String effort;
        Scanner console = new Scanner(System.in);
        Random gen = new Random();

        /* INPUTS */

        // RIDE LENGTH
        System.out.print("For how long do you want to bike?\nDuration " +
                "(seconds): ");

        try {
            duration = Integer.parseInt(console.next());

            while(duration < 1) {
                System.out.println("Please enter a real duration value.");
                duration = Integer.parseInt(console.next());
            }
        } catch(Exception ex) {
            System.out.println("That is not an integer, duration set to 10 " +
                    "seconds.");
            duration = 10;
        }

        // EFFORT
        System.out.print("How much effort will you use? (Little, Some, " +
                "Much)\nEffort: ");
        effort = console.next();

        // ROUTE COLOR
        System.out.print("What color do you want your route to be?\n E.g." +
                " Black, Red, Green, Yellow, Blue, Cyan, White\nColor: ");
        color = console.next();

        // ROUTE DETAIL
        System.out.println("How detailed would you like your route to be? " +
                "(frames per second)\nLevel of Detail: ");

        try {
            detail = Integer.parseInt(console.next());

            while(detail < 1) {
                System.out.println("Please enter an integer greater than 0.");
                detail = Integer.parseInt(console.next());
            }
        } catch(Exception ex) {
            System.out.println("That is not an integer, detail set to 10.");
            detail = 10;
        }

        console.close();

        /* RIDE */

        System.out.println("Preparing your ride...");
        Bicycle bike = new Bicycle(color, effort, detail, duration);
        System.out.print("Your ride has begun!\n\n" +
                "ROUTE");

        int θ = -15;
        for(int i=0; i<duration; i++) {
            bike.ride((double)(θ));
            θ = gen.nextInt(35) - 20;
        }

        /* STATISTICS */

        double[] bikeStats = bike.getStats();
        System.out.println("\n\nRide complete. STATS\n" +
                "Average Velocity: " + bikeStats[0] + " km/h\n" +
                "Total Distance: " + bikeStats[1] + " m\n" +
                "Change in Altitude: " + bikeStats[2] + " m");
    }
}