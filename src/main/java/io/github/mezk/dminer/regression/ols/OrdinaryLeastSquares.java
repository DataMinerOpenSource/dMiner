package io.github.mezk.dminer.regression.ols;

/**
 * Declares util methods for
 * <a href="https://en.wikipedia.org/wiki/Ordinary_least_squares">
 *     Ordinary Least Squares (OLS)</a>
 * method.
 *
 * @author Andrei Selkin
 * @author Vladislav Lisetskiy
 */
public interface OrdinaryLeastSquares {

    /**
     * Performs a regression on input data nd outputs a RegressionResults object.
     * @param inputData input data.
     * @return RegressionResults object which acts as a container of regression output.
     */
    RegressionResults regress(double[][] inputData);

    /**
     * Returns the "predicted" y value associated with the supplied
     * x value, based on the data that has been added to the model
     * when a method is activated.
     * @param xValues input x values.
     * @param coefficientA coefficient a.
     * @param coefficientB coefficient b.
     * @return array of predicted y values.
     */
    double[] predictFunctionValues(double[] xValues,
                                   double coefficientA, double coefficientB);
}
