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
        //       sin(y + 2) - x = 15;
        //       y + cos(x - 2) = 0.5;
        //
        //        x=sin(y0+2)-15;
        //        y=0.5-cos(x0-2);
        //        d1=sin(y+2)-x-15;
        //        d2=y+cos(x-2)-0.5;
        //        x0=x;
        //        y0=y;
        x=signifyX(tmpCount);
        y=signifyY(tmpCount);
        d1=signifyd1(tmpCount);
        d2=signifyd2(tmpCount);
        x0=x;
        y0=y;
    } while (Math.abs(d1) > EPS && Math.abs(d2)> EPS);
    return x;}

}
