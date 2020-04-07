package Part1.lab3;

import static Part1.lab3.gui.UserInterface.tmpCounter;

public class Func {
    public static double MIN_RANGE ;
    public static double MAX_RANGE ;

    public static double f(double x) {
        switch (tmpCounter) {
            case 1:
                 MIN_RANGE = 0;
                MAX_RANGE = 5;
                return - Math.pow(x,3) - 1;
                //return Math.abs(Math.pow(x, 3) - x) - 2*x + 2;

            case 2:
                 MIN_RANGE = -15;
                 MAX_RANGE = 10;
                return Math.pow(x, 3) - 3 * Math.pow(x, 2) + 6 * x + 3;
            case 3:
                MIN_RANGE = -1;
                 MAX_RANGE = 1;
                return Math.pow(x,5) - 1;
        }
        return 0;
    }


    public static double df(double x) {
        switch (tmpCounter) {
            case 1:
                return - 3*Math.pow(x,2);
                //return 3*x*x *Math.abs(x*x*x - x)/(x*x*x -x) -2;
            case 2:
                return 3 * Math.pow(x, 2) - 6 * x + 6;
            case 3:

                return 5*Math.pow(x, 4);
        }
        return 0;
    }
}
