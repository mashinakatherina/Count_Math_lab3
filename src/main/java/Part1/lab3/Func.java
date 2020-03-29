package Part1.lab3;

import static lab3.gui.UserInterface.tmpCounter;

public class Func {


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
