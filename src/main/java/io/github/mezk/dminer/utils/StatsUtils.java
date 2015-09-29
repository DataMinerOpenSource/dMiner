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
    public static double correlation(double[] x, double[] y) {

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
        final double squaredAverageX = Math.pow(averageX, 2);
        final double squaredAverageY = Math.pow(averageY, 2);
        final double denominator = Math.sqrt(
            (sumXX - n * squaredAverageX) * (sumYY - n * squaredAverageY));

        correlationCoefficient = numerator / denominator;
        return correlationCoefficient;
    }

    /**
     * Calculates the square root of the mean/average
     * of the square of all of the error.
     * The use of root mean squared error (RMSE) is very
     * common and it makes an excellent general purpose error
     * metric for numerical predictions.
     * Compared to the similar Mean Absolute Error,
     * RMSE amplifies and severely punishes large errors.
     * @param yExpected expected values.
     * @param yActual actual values.
     * @return root mean squared error.
     */
    public static double rootMeanSquaredError(double[] yExpected, double[] yActual) {

        if (yExpected.length != yActual.length) {
            throw new IllegalArgumentException("Can not calculate "
                + "root mean squared error of "
                + "multiple different lengths: yExpected.length="
                + yExpected.length + "yActual.length=" + yActual.length);
        }

        double sumSquared = 0;
        for (int i = 0; i < yExpected.length; i++) {
            final double difference = yExpected[i] - yActual[i];
            sumSquared += StrictMath.pow(difference, 2);
        }

        final double n = yExpected.length;
        final double meanSquaredError = sumSquared / n;
        return StrictMath.sqrt(meanSquaredError);
    }
}
