package Part2.lab3;

import static Part2.lab3.gui.UserInterface.tmpCount;

public class Iterations {

    public static final double EPS = 0.0001;
    private static double x = 0;
    private static double y = 0;
    private static double d1 = 0;
    private static double d2 = 0;
    public static double y0 = 0;
    public static double x0 = 0;
    //static int tmpCount = 1;
    public static double countX() {
        do
        {
            x=signifyX(tmpCount);
            y=signifyY(tmpCount);
            d1= signifyD1(tmpCount);
            d2= signifyD2(tmpCount);
            x0=x;
            y0=y;
        } while (Math.abs(d1) > EPS && Math.abs(d2)> EPS);
        x0 =0;
        y0= 0;
        return x;
    } public static double countY() {
        do
        {
            x=signifyX(tmpCount);
            y=signifyY(tmpCount);
            d1= signifyD1(tmpCount);
            d2= signifyD2(tmpCount);
            x0=x;
            y0=y;
        } while (Math.abs(d1) > EPS && Math.abs(d2)> EPS);
        x0 =0;
        y0= 0;
        return y;
    }

    public static double signifyX(int tmpCounter) {
        switch (tmpCounter) {
            case 0:
                return Math.sin(y0+2)-15;
            case 1:
               // System.out.println(Math.pow(y0+2, 1d/3));
                return Math.pow(y0+2, 1d/3);

            case 2:

                return Math.pow(y0+5, 0.5);
            case 3:

                return Math.pow(y0, 0.5);
        }
        return 0;
    }

    public static double signifyY(int tmpCounter) {
        switch (tmpCounter) {
            case 0:
                return 0.5-Math.cos(x0-2);
            case 1:

                return Math.pow(x0,3) - 2*x0 + 1;

            case 2:

                return Math.pow(x0 + x0*3,0.5) + 2;
            case 3:

                return Math.pow(x0, 3)+x0;
        }
        return 0;
    }

    public static double signifyD1(int tmpCounter) {
        switch (tmpCounter) {
            case 0:
                return Math.sin(y+2)-x-15;
            case 1:

                return Math.pow(x,3) - 2 - y;

            case 2:

                return Math.pow(x, 2) - 5 - y;
            case 3:

                return Math.pow(x, 2) - y;
        }
        return 0;
    }

    public static double signifyD2(int tmpCounter) {
        switch (tmpCounter) {
            case 0:
                return y+Math.cos(x-2)-0.5;
            case 1:

                return Math.pow(x,3) - 2*x + 1 - y;

            case 2:

                return Math.pow(x + x*3,0.5) + 2 - y;
            case 3:

                return Math.pow(x, 3) + x - y;
        }
        return 0;
    }

}
