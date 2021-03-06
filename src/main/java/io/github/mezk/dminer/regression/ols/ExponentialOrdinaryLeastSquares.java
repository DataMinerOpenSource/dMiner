package io.github.mezk.dminer.regression.ols;

import io.github.mezk.dminer.utils.ArrayMath;
import io.github.mezk.dminer.utils.StatsUtils;

/**
 * Ordinary Least Squares method for exponential function.
 * Exponential function is a relation of the form
 * y = a * e ^ b * x
 *
 * @author Andrei Selkin
 * @author Vladislav Lisetskiy
 */
public class ExponentialOrdinaryLeastSquares extends LinearOrdinaryLeastSquares {

    @Override
    public RegressionResults regress(double[][] inputData) {

        final double[][] convertedInputData = new double[inputData.length][inputData[1].length];
        convertedInputData[0] = inputData[0];
        convertedInputData[1] = ArrayMath.log(inputData[1]);

        final RegressionResults linearOslResult = super.regress(convertedInputData);
        final double a = linearOslResult.getCoefficientA();
        final double convertedB = linearOslResult.getCoefficientB();
        final double b = Math.exp(convertedB);

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
    public double[] predictFunctionValues(double[] xValues, double a, double b) {
        final double[] result = new double[xValues.length];
        for (int i = 0; i < xValues.length; i++) {
            result[i] = a * Math.exp(xValues[i] * b);
        }
        return result;
    }
}
