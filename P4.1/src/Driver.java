import java.util.Scanner;

/**
 * Provides the user with the net cost and profit of owning a hybrid car.
 *
 * @author Ian Balaguera
 * @version 10.11.18
 */
public class Driver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int mpy;
        int budgetg;
        int budgeth;
        double mpgg;
        double mpgh;

        System.out.println("Gas car cost: ");
        budgetg = Integer.parseInt(in.next());

        System.out.println("Estimated miles per gallon (gas car): ");
        mpgg = Double.parseDouble(in.next());

        System.out.println("\nHybrid car cost: ");
        budgeth = Integer.parseInt(in.next());

        System.out.println("Estimated miles per gallon (hybrid car): ");
        mpgh = Double.parseDouble(in.next());

        System.out.println("\nMiles per year: ");
        mpy = Integer.parseInt(in.next());

        Car gas = new Car(budgetg, mpy, mpgg, false);
        Car hybrid = new Car(budgeth, mpy, mpgh, true);

        double costh = hybrid.returnCost();
        System.out.printf("\nNet Hybrid Car Cost: $%.2f", costh);
        double costg = gas.returnCost();
        System.out.printf("\nNet Gas Car Cost: $%.2f", costg);
        if(costg > costh) {
            System.out.printf("\nGas car costs $%.2f more.",(costg - costh));
        } else {
            System.out.printf("\nHybrid car costs $%.2f more.",(costh - costg));
        }
    }
}