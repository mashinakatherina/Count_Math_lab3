package Part1.lab3;

import static Part1.lab3.gui.UserInterface.tmpCounter;

public class Iterations {
    private static final double MIN_RANGE = -15;
    private static final double MAX_RANGE = 15;
    public static final double EPS = 0.0001;
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
        } while (Math.abs(x - x0) >= EPS || count <= 10000);
        return x0;
    }

    public static double f(double x) {
        switch (tmpCounter) {
            case 1:
                return Math.abs(Math.pow(x, 3) - x) - 2*x + 2;
            case 2:
                return Math.pow(x, 3) - 3 * Math.pow(x, 2) + 6 * x + 3;
            case 3:

                return -5*Math.pow(x,2) - 2*x;
        }
        return 0;
    }


    public static double df(double x) {
        switch (tmpCounter) {
            case 1:
                return 3*x*x *Math.abs(x*x*x - x)/(x*x*x -x) -2;
            case 2:
                return 3 * Math.pow(x, 2) - 6 * x + 6;
            case 3:

                return -10 * x -2;
        }
        return 0;
    }
}