package Part2.lab3;

//import static Part2.lab3.gui.UserInterface.tmpCount;

public class Iterations {

    public static final double EPS = 0.0001;
    public static double x;
    public static double y;
    private static double d1 = 0;
    private static double d2 = 0;
    public static double y0 = 0;
    public static double x0 = 0;
    static int tmpCount = 3;
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
           // System.out.println(x);
            y=signifyY(tmpCount);
           // System.out.println(y);
            d1= signifyD1(tmpCount);
            d2= signifyD2(tmpCount);
            x0=x;
            y0=y;
        } while (Math.abs(d1) > EPS && Math.abs(d2)> EPS);
        return x;
    }
//                    case "1":
//                        this.baseFunction1 = arg -> Math.pow(arg,5);
//                        this.baseFunction2 = arg -> Math.pow(arg,3) - 2*arg + 1;
//                        tmpCount = 1;
//                        break;
//                    case "2":
//                        this.baseFunction1 = arg -> (Math.pow(arg, 2) + 5);
//                        this.baseFunction2 = arg -> Math.pow(arg*arg + arg*3,1.5) + 2;
//                        tmpCount = 2;
//                        break;
//                    case "3":
//                        this.baseFunction1 = arg -> arg * arg;
//                        this.baseFunction2 = arg -> (arg*arg*arg)+arg;
//                        tmpCount = 3;
//                        break;
    public static double signifyX(int tmpCounter) {
        switch (tmpCounter) {
            case 0:
                return Math.sin(y0+2)-15;
            case 1:

                return Math.pow(y0, 0.2);

            case 2:

                return Math.pow(y0-5, 0.5);
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

                return Math.pow(x0*x0 + x0*3,1.5) + 2;
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

                return Math.pow(x,5) - y;

            case 2:

                return Math.pow(x, 2) + 5 - y;
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

                return Math.pow(x*x + x*3,1.5) + 2 - y;
            case 3:

                return Math.pow(x, 3) + x - y;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(countX());
    }
}
