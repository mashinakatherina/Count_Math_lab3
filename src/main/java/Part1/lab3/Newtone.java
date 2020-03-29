package Part1.lab3;


import static Part1.lab3.Func.*;
import static Part1.lab3.Func.MAX_RANGE;
import static Part1.lab3.Func.MIN_RANGE;
public class Newtone  {

    private static final double EPS = 0.0001;
    private static double x = 0;


    public static double countXByNewtone() {
        int n = 0;
        if (f(MIN_RANGE) * df(MAX_RANGE) < 0){
            x =MIN_RANGE;
        }
        else {
            x = MAX_RANGE;
        }
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