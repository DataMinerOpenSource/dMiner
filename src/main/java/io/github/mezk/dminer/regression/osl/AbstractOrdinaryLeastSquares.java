package io.github.mezk.dminer.regression.osl;

/**
 * Contains util methods for Ordinary Least Squares (OLS) method.
 *
 * @author Andrei Selkin
 * @author Vladislav Lisetskiy
 */
public abstract class AbstractOrdinaryLeastSquares {

    /**
     * Processes input data.
     * @param inputData input data.
     * @return result of processing.
     */
    abstract Result process(double[][] inputData);

    /**
     * Calculates values of a function with found coefficients.
     * @param xInput input values of x.
     * @param coefficientA coefficient A.
     * @param coefficientB coefficient B.
     * @return array of function values.
     */
    abstract double[] calculateFunctionValues(
            double[] xInput, double coefficientA, double coefficientB);
}
