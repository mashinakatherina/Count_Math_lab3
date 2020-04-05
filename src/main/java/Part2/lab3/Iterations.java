package Part2.lab3;

import static Part2.lab3.Func.*;
import static Part2.lab3.gui.UserInterface.tmpCount;

public class Iterations {

    public static final double EPS = 0.0001;
    public static double x;
    public static double y;
    public static double d1;
    public static double d2;

    public static double countX() {


    do
    {
        x=signifyX(tmpCount);
        y=signifyY(tmpCount);
        d1=signifyd1(tmpCount);
        d2=signifyd2(tmpCount);
        x0=x;
        y0=y;
    } while (Math.abs(d1) > EPS && Math.abs(d2)> EPS);
    return x;}

}
