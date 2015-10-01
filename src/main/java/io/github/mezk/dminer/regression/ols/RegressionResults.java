package io.github.mezk.dminer.regression.ols;

import org.apache.commons.lang3.ArrayUtils;

/**
 * A result of Ordinary Least Squares method.
 *
 * @author Andrei Selkin
 */
public final class RegressionResults {

    /** Coefficient a.*/
    private double coefficientA;

    /** Coefficient b.*/
    private double coefficientB;

    /** Correlation coefficient.*/
    private double correlationCoefficient;

    /** Root mean squared error (RMSE). */
    private double rootMeanSquaredError;

    /** Predicted function values. */
    private double[] predictedFunctionValues = ArrayUtils.EMPTY_DOUBLE_ARRAY;

    public double getCoefficientA() {
        return this.coefficientA;
    }

    public void setCoefficientA(double coefficientA) {
        this.coefficientA = coefficientA;
    }

    public double getCoefficientB() {
        return this.coefficientB;
    }

    public void setCoefficientB(double coefficientB) {
        this.coefficientB = coefficientB;
    }

    public double getCorrelationCoefficient() {
        return this.correlationCoefficient;
    }

    public void setCorrelationCoefficient(double correlationCoefficient) {
        this.correlationCoefficient = correlationCoefficient;
    }

    public double[] getPredictedFunctionValues() {
        return this.predictedFunctionValues.clone();
    }

    public void setPredictedFunctionValues(double[] predictedFunctionValues) {
        this.predictedFunctionValues = predictedFunctionValues.clone();
    }

    public double getRootMeanSquaredError() {
        return this.rootMeanSquaredError;
    }

    public void setRootMeanSquaredError(double rootMeanSquaredError) {
        this.rootMeanSquaredError = rootMeanSquaredError;
    }
}
