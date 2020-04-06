package Part1.lab3;

import static Part1.lab3.Func.*;
public class Iterations {

    public static final double EPS = 0.0001;
    public static final int MAX_NUMBER_OF_ITERATIONS = 10000;
    private static double x ;
    private static double lambda;

    public static double countStartX(double minRange, double maxRange, double x) {
        double a = f(minRange);
        double b = f(maxRange);
        double c = df(x);
        if (a >= b && a >= c) return minRange;
        if (b >= a && b >= c) return maxRange;
        return x;
    }


    public static double getLambda(double x) {
        return 1.0 / df(x);
    }


    public static double countXByIterations() {
        x = countStartX(MIN_RANGE, MAX_RANGE, x);
        lambda = getLambda(x);
        double x0;
        double fx;
        int count = 0;

        do {
            x0 = x;
            x = x - lambda * (f(x));
            fx = f(x0);
            count++;
        } while (Math.abs(x - x0) >= EPS || count <= MAX_NUMBER_OF_ITERATIONS);
        return x0;
    }

}