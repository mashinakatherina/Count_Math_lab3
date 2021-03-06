package Part2.lab3;

public class Iterations {
    public static final double EPS = 0.0001;
    public static final int MAX_NUMBER_OF_ITERATIONS = 100000;
    public static double y0 = 0;
    public static double x0 = 0;
    
    public static double countX(Function f1, Function f2) {
        int k = 0;
        double s1, s2, x, y;
        do
        {
            x = f1.signifyX(y0);
            y = f2.getValue(x0);
            s1 = f1.equateToZero(x, y);
            s2 = f2.equateToZero(x, y);
            x0 = x;
            y0 = y;
            k++;
        } while (Math.abs(s1) > EPS && Math.abs(s2)> EPS || k < MAX_NUMBER_OF_ITERATIONS);
        x0 = 0;
        y0 = 0;
        return x;
    }

    public static double countY(Function f1, Function f2) {
        double s1, s2, y;
        int k =0;
        do
        {
            double x = f1.signifyX(y0);
            y = f2.getValue(x0);
            s1 = f1.equateToZero(x, y);
            s2 = f2.equateToZero(x, y);
            x0 = x;
            y0 = y;
            k ++;
        } while (Math.abs(s1) > EPS && Math.abs(s2)> EPS || k < MAX_NUMBER_OF_ITERATIONS);
        x0 = 0;
        y0 = 0;
        return y;
    }
}