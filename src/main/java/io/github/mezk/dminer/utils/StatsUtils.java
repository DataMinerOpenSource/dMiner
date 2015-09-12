package io.github.mezk.dminer.utils;

/**
 * Util class which contains different statistics algorithms and methods.
 *
 * @author Andrei Selkin
 */
public final strictfp class StatsUtils {

    /**
     * Default constructor is declared as private,
     * because MathUtils is a util class.
     */
    private StatsUtils() { }

    /**
     * Calculates linear correlation between x and y.
     * @param x values.
     * @param y values.
     * @return linear correlation coefficient.
     */
    public static double calculateLinearCorrelationCoefficient(
        double[] x, double[] y) {

        if (x.length != y.length) {
            throw new IllegalArgumentException("Can't calculate "
                + "correlation coefficient of "
                + "multiple different lengths: a.length="
                + x.length + "b.length=" + y.length);
        }
        final double correlationCoefficient;

        final double n = x.length;

        final double sumXY = ArrayMath.dotProduct(x, y);
        final double averageX = ArrayMath.mean(x);
        final double averageY = ArrayMath.mean(y);
        final double numerator = sumXY - n * averageX * averageY;

        final double sumXX = ArrayMath.sumSquared(x);
        final double sumYY = ArrayMath.sumSquared(y);
        final double squaredAverageX = StrictMath.pow(averageX, 2);
        final double squaredAverageY = StrictMath.pow(averageY, 2);
        final double denominator =
            StrictMath.sqrt((sumXX - n * squaredAverageX) * (sumYY - n * squaredAverageY));

        correlationCoefficient = numerator / denominator;
        return correlationCoefficient;
    }

}
