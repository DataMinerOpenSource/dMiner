package io.github.mezk.dminer.regression.ols;

import io.github.mezk.dminer.utils.ArrayMath;
import io.github.mezk.dminer.utils.StatsUtils;

/**
 * Ordinary Least Squares method for rational function.
 * Rational function is a relation of the form
 * y = x / (a * x + b)
 *
 * @author Vladislav Lisetskiy
 */
public class RationalOrdinaryLeastSquares extends LinearOrdinaryLeastSquares {

    @Override
    public Result process(double[][] inputData) {
        final double[][] convertedInputData = new double[inputData[0].length][inputData[1].length];
        convertedInputData[0] = ArrayMath.pow(inputData[0], -1);
        convertedInputData[1] = ArrayMath.pow(inputData[1], -1);

        final Result linearOslResult = super.process(convertedInputData);
        final double a = linearOslResult.getCoefficientB();
        final double b = linearOslResult.getCoefficientA();

        final double[] actualValuesOfLinearFunction =
            this.calculateFunctionValues(inputData[0], a, b);
        final double correlationCoefficient = StatsUtils.calculateLinearCorrelationCoefficient(
            inputData[0], actualValuesOfLinearFunction);

        final Result result = new Result();
        result.setCoefficientA(a);
        result.setCoefficientB(b);
        result.setCorrelationCoefficient(correlationCoefficient);
        return result;
    }

    @Override
    public strictfp double[] calculateFunctionValues(double[] x, double a, double b) {
        final double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = x[i] / (a * x[i] + b);
        }
        return result;
    }
}
