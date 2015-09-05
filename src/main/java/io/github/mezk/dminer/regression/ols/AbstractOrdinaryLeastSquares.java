package io.github.mezk.dminer.regression.ols;

/**
 * Contains util methods for Ordinary Least Squares (OLS) method.
 *
 * @author Andrei Selkin
 */
public abstract class AbstractOrdinaryLeastSquares {

    /**
     * Processes input data.
     * @param inputData input data.
     * @return result of processing.
     */
    abstract Result process(double[][] inputData);

    /**
     * Calculates sum of array's elements.
     * @param array array.
     * @return sum of array's elements.
     */
    public static strictfp double sum(double[] array) {
        double sum = 0;
        for (double element : array) {
            sum += element;
        }
        return sum;
    }

    /**
     * Calculates sum of products of first array;s and second array's elements.
     * @param firstArray first array.
     * @param secondArray second array.
     * @return sum of products of first array;s and second array's elements.
     */
    public static strictfp double sumOfProducts(double[] firstArray, double[] secondArray) {
        if (firstArray.length != secondArray.length) {
            throw new IllegalArgumentException("A length of first array is not equal"
                + " to second array length.");
        }

        double sum = 0;
        for (int i = 0; i < firstArray.length; i++) {
            sum += firstArray[i] * secondArray[i];
        }
        return sum;
    }

    // TODO: implement method which calculates correlation coefficient.
}
