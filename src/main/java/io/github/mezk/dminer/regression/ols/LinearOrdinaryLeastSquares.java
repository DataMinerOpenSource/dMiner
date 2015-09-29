package io.github.mezk.dminer.regression.ols;

import io.github.mezk.dminer.utils.ArrayMath;
import io.github.mezk.dminer.utils.StatsUtils;

/**
 * Ordinary Least Squares method for linear function.
 * Linear function is a relation of the form
 * y = a * x + b
 *
 * @author Andrei Selkin
 * @author Vladislav Lisetskiy
 */
public class LinearOrdinaryLeastSquares implements OrdinaryLeastSquares {

    @Override
    public RegressionResults regress(double[][] inputData) {

        final double sumX = ArrayMath.sum(inputData[0]);
        final double sumY = ArrayMath.sum(inputData[1]);
        final double sumXX = ArrayMath.sumSquared(inputData[0]);
        final double sumXY = ArrayMath.dotProduct(inputData[0], inputData[1]);
        final double squaredSumX = Math.pow(sumX, 2);

        final double n = inputData[0].length;
        final double a = (n * sumXY - sumX * sumY) / (n * sumXX - squaredSumX);
        final double b = (sumY - a * sumX) / n;

        final double[] predictedFunctionValues = this.predictFunctionValues(inputData[0], a, b);
        final double correlationCoefficient = StatsUtils.correlation(inputData[0], inputData[1]);
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
            result[i] = a * xValues[i] + b;
        }
        return result;
    }
}
