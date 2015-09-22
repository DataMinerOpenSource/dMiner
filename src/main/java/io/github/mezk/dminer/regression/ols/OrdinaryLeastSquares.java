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
     * Processes input data.
     * @param inputData input data.
     * @return result of processing.
     */
    Result process(double[][] inputData);

    /**
     * Calculates values of a function with the coefficients.
     * @param xInput input values of x.
     * @param coefficientA coefficient A.
     * @param coefficientB coefficient B.
     * @return array of function values.
     */
    double[] calculateFunctionValues(double[] xInput,
                                     double coefficientA, double coefficientB);
}
