package io.github.mezk.dminer.utils;

/**
 * Util class to perform math operations with arrays.
 *
 * @author Andrei Selkin
 */
public final strictfp class ArrayMath {

    /**
     * Default constructor is declared as private,
     * because MathUtils is a util class.
     */
    private ArrayMath() { }

    /**
     * Calculates natural logarithm of each element in array.
     * @param a an array.
     * @return an array which is a result of calculations.
     */
    public static double[] log(double[] a) {
        final double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = Math.log(a[i]);
        }
        return result;
    }

    /**
     * Raises each value of the array to the power of the second argument.
     * @param a an array.
     * @param c the exponent.
     * @return the array which is a result of calculations.
     */
    public static double[] pow(double[] a, double c) {
        final double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = Math.pow(a[i], c);
        }
        return result;
    }

    /**
     * Returns the sum of array elements.
     * @param a an array.
     * @return the sum of array elements.
     */
    public static double sum(double[] a) {
        return sum(a, 0, a.length);
    }

    /**
     * Returns the sum of array elements from the start index to the end index.
     * @param a an array.
     * @param fromIndex start index.
     * @param toIndex end index.
     * @return the sum of array elements from the start index to the end index.
     */
    public static double sum(double[] a, int fromIndex, int toIndex) {
        double result = 0.0;
        for (int i = fromIndex; i < toIndex; i++) {
            result += a[i];
        }
        return result;
    }

    /**
     * Returns the sum of squared array elements.
     * @param a an array.
     * @return the sum of squared array elements.
     */
    public static double sumSquared(double[] a) {
        double result = 0.0;
        for (double anA : a) {
            result += anA * anA;
        }
        return result;
    }

    /**
     * Calculates the sum of products of elements of the first array and
     * elements of the second array.
     * @param a first array.
     * @param b second array.
     * @return the sum of products of elements of the first array and elements of the second array.
     */
    public static double dotProduct(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new RuntimeException("Can't calculate dot product of "
                + "multiple different lengths: a.length=" + a.length + " b.length=" + b.length);
        }
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }

    /**
     * Calculates mean of array elements.
     * @param a an array.
     * @return mean of array elements.
     */
    public static double mean(double[] a) {
        return sum(a) / a.length;
    }
}
