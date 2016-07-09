package io.github.mezk.dminer.regression.ols;

import java.util.Arrays;
import java.util.function.DoublePredicate;

import io.github.mezk.dminer.utils.ArrayMath;
import io.github.mezk.dminer.utils.StatsUtils;

/**
 * Ordinary Least Squares method for logarithmic function.
 * Logarithmic function is a relation of the form
 * y = a * ln(x) + b
 *
 * @author Andrei Selkin
 * @author Vladislav Lisetskiy
 */
public class LogarithmicOrdinaryLeastSquares extends LinearOrdinaryLeastSquares {

    @Override
    public RegressionResults regress(double[][] inputData) {

        if (Arrays.stream(inputData[0]).filter(new DoublePredicate() {
            @Override
            public boolean test(double x) {
                return x <= 0.0;
            }
        }).toArray().length != 0) {
            throw new IllegalArgumentException("Input data contains argument "
                + "which is not greater than zero");
        }

        final double[][] convertedInputData = new double[inputData.length][inputData[1].length];
        convertedInputData[0] = ArrayMath.log(inputData[0]);
        convertedInputData[1] = inputData[1];

        final RegressionResults linearOslResult = super.regress(convertedInputData);
        final double a = linearOslResult.getCoefficientA();
        final double b = linearOslResult.getCoefficientB();

        final double[] predictedFunctionValues = this.predictFunctionValues(inputData[0], a, b);
        final double correlationCoefficient = linearOslResult.getCorrelationCoefficient();
        final double rootMeanSquaredError = StatsUtils.rootMeanSquaredError(
            inputData[1], predictedFunctionValues);

        final RegressionResults results = new RegressionResults();
        results.setCoefficientA(a);
        results.setCoefficientB(b);
        results.setPredictedFunctionValues(predictedFunctionValues);
        results.setCorrelationCoefficient(correlationCoefficient);
        results.setRootMeanSquaredError(rootMeanSquaredError);
        return results;
    }

    @Override
    public double[] predictFunctionValues(
        double[] xValues, double a, double b) {
        final double[] result = new double[xValues.length];
        for (int i = 0; i < xValues.length; i++) {
            result[i] = a * StrictMath.log(xValues[i]) + b;
        }
        return result;
    }
}
