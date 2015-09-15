package io.github.mezk.dminer.regression.osl;

import io.github.mezk.dminer.utils.ArrayMath;
import io.github.mezk.dminer.utils.StatsUtils;

/**
 * Ordinary Least Squares method for linear function.
 *
 * @author Andrei Selkin
 * @author Vladislav Lisetskiy
 */
public class LinearOrdinaryLeastSquares extends AbstractOrdinaryLeastSquares {

    @Override
    public strictfp Result process(double[][] inputData) {

        final double sumX = ArrayMath.sum(inputData[0]);
        final double sumY = ArrayMath.sum(inputData[1]);
        final double sumXX = ArrayMath.sumSquared(inputData[0]);
        final double sumXY = ArrayMath.dotProduct(inputData[0], inputData[1]);
        final double squaredSumX = StrictMath.pow(sumX, 2);

        final double n = inputData[0].length;
        final double a = (n * sumXY - sumX * sumY) / (n * sumXX - squaredSumX);
        final double b = (sumY - a * sumX) / n;

        final Result result = new Result();
        result.setCoefficientA(a);
        result.setCoefficientB(b);
        result.setCorrelationCoefficient(
                StatsUtils.calculateLinearCorrelationCoefficient(
                        inputData[0], calculateFunctionValues(inputData[0], a, b)));
        return result;
    }

    @Override
    public strictfp double[] calculateFunctionValues(
            double[] x, double a, double b) {
        final double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = a * x[i] + b;
        }
        return result;
    }
}
