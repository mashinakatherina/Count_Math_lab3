package Part2.lab3;

import static Part2.lab3.Iterations.x;
import static Part2.lab3.Iterations.y;

public class Func {
    public static double y0 = 0;
    public static double x0 = 0;
    public static double signifyX(int tmpCounter) {
        switch (tmpCounter) {
            case 1:

                return Math.pow(y0, 1/5);

            case 2:

                return Math.pow(y0-5, 1/2);
            case 3:

                return Math.pow(y0, 1/2);
        }
        return 0;
    }
    public static double signifyY(int tmpCounter) {
        switch (tmpCounter) {
            case 1:

                return Math.pow(x0,3) - 2*x0 + 1;

            case 2:

                return Math.pow(x0*x0 + x0*3,1.5) + 2;
            case 3:

                return Math.pow(x0, 3)+x0;
        }
        return 0;
    }
    public static double signifyd1(int tmpCounter) {
        switch (tmpCounter) {
            case 1:

                return Math.pow(x,5) - y;

            case 2:

                return Math.pow(x, 2) + 5 - y;
            case 3:

                return Math.pow(x, 2) - y;
        }
        return 0;
    }
    public static double signifyd2(int tmpCounter) {
        switch (tmpCounter) {
            case 1:

                return Math.pow(x,3) - 2*x + 1 - y;

            case 2:

                return Math.pow(x*x + x*3,1.5) + 2 - y;
            case 3:

                return Math.pow(x, 3) + x - y;
        }
        return 0;
    }

}
