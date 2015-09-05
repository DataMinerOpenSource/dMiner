package io.github.mezk.dminer.regression.ols;

/**
 * A result of Ordinary Least Squares method.
 *
 * @author Andrei Selkin
 */
public final class Result {

    /** Coefficient a.*/
    private double coefficientA;

    /** Coefficient b.*/
    private double coefficientB;

    /** Correlation coefficient.*/
    private double correlationCoefficient;

    public double getCoefficientA() {
        return coefficientA;
    }

    public void setCoefficientA(double coefficientA) {
        this.coefficientA = coefficientA;
    }

    public double getCoefficientB() {
        return coefficientB;
    }

    public void setCoefficientB(double coefficientB) {
        this.coefficientB = coefficientB;
    }

    public double getCorrelationCoefficient() {
        return correlationCoefficient;
    }

    public void setCorrelationCoefficient(double correlationCoefficient) {
        this.correlationCoefficient = correlationCoefficient;
    }
}
