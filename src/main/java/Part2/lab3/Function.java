package Part2.lab3;

public interface Function {
    double getValue(double arg);
    default double equateToZero(double x, double y) {
        return getValue(x) - y;
    };
    default double signifyX(double y) {
        return 0d;
    };
}
