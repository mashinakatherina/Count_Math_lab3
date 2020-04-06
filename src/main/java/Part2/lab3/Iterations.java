package Part2.lab3;

public class Iterations {
    public static final double EPS = 0.0001;
    public static double y0 = 0;
    public static double x0 = 0;
    public static double countX(Function f1, Function f2) {
        double d1, d2, x;
        do
        {
            x = f1.expressX(y0);
            double y = f2.getValue(x0);
            d1 = f1.equateToZero(x, y);
            d2 = f2.equateToZero(x, y);
            x0 = x;
            y0 = y;
        } while (Math.abs(d1) > EPS && Math.abs(d2)> EPS);
        x0 = 0;
        y0 = 0;
        return x;
    }public static double countY(Function f1, Function f2) {
        double d1, d2, x, y;
        do
        {
            x = f1.expressX(y0);
            y = f2.getValue(x0);
            d1 = f1.equateToZero(x, y);
            d2 = f2.equateToZero(x, y);
            x0 = x;
            y0 = y;
        } while (Math.abs(d1) > EPS && Math.abs(d2)> EPS);
        x0 = 0;
        y0 = 0;
        return y;
    }
}