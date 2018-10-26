/**
 * Tests the program.
 *
 * @author Ian Balaguera
 * @version 10.2.18
 */
public class Tester {
    public static void main(String[] args) {
        Bicycle test = new Bicycle("white", "much", 1, 1);
        test.ride(0);
        double[] testStats = test.getStats();
        System.out.println("\n\nActual Stats:\n" +
                "Average Velocity: " + testStats[0] + "\n" +
                "Distance: " + testStats[1] + "\n" +
                "Change in Altitude: " + testStats[2] + "\n");
        System.out.println("Expected Stats:\n" +
                "Average Velocity: 1.61\n" + // 3 / 6.7 * 3.6
                "Distance: 0.45\n" + // 3 / 6.7
                "Change in Altitude: 0.0");
    }
}
