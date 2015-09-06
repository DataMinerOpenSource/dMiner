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
}
