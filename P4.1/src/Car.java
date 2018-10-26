/**
 * Creates Car objects with monetary statistics.
 *
 * @author Ian Balaguera
 * @version 10.11.18
 */
public class Car {
    private int val;
    private int mpy;
    private double mpg;
    private boolean hybrid;
    private static final double GAS_PRICE = 2.70;

    /**
     * Default Car constructor.
     */
    public Car() {
        this(25000,15000,25,false);
    }

    /**
     * Overloaded Car constructor.
     * @param val Cost of a new car.
     * @param mpy Miles driven per year.
     * @param mpg Miles per gallon.
     * @param hybrid Whether the car is hybrid or not.
     */
    public Car(int val, int mpy, double mpg, boolean hybrid) {
        this.val = val;
        this.mpy = mpy;
        this.mpg = mpg;
        this.hybrid = hybrid;
    }

    /**
     * Calculates the net cost.
     * @return Net cost.
     */
    public double returnCost() {
        return val + calcGasCost() - calcResale();
    }

    private double calcResale() {
        double resaleVal;
        if(hybrid) {
            resaleVal = (val * Math.pow(.85, 5));
        } else {
            resaleVal = (val * Math.pow(.7, 5));
        }
        return resaleVal;
    }

    private double calcGasCost() {
        double gasCost;
        gasCost = (mpy / mpg * GAS_PRICE);
        return gasCost;
    }
}
