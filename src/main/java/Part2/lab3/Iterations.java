package Part2.lab3;

public class Iterations {

    public static final double EPS = 0.0001;
    private static double x;
    private static double y;

    public static double countX() {
        double d1;
        double d2;
        double y0 = 0;
        double x0 = 0;
    do
    {
        x=Math.sin(y0+2)-15;
        y=0.5-Math.cos(x0-2);
        d1=Math.sin(y+2)-x-15;
        d2=y+Math.cos(x-2)-0.5;
        x0=x;
        y0=y;
    } while (Math.abs(d1) > EPS && Math.abs(d2)> EPS);
    return x;}

}
