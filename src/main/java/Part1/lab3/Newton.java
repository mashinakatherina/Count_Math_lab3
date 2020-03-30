package Part1.lab3;


import static Part1.lab3.Func.*;
import static Part1.lab3.Func.MAX_RANGE;
import static Part1.lab3.Func.MIN_RANGE;
public class Newton {

    private static final double EPS = 0.0001;
    private static double x = 0;

    public static double countStartX(double minRange, double maxRange, double x) {
        if (f(minRange)*df(maxRange) < 0) {
            return minRange;
        }
        else
        {
            return maxRange;
        }
    }

    public static double countXByNewton() {
        int n = 0;
        x = countStartX(MIN_RANGE, MAX_RANGE, x);
        double counter = 0;
        counter = Math.abs(df(x));
        while (counter > EPS) {
            x = x - (f(x) / df(x));
            n ++;
            counter--;
        }
        return x;
    }

}