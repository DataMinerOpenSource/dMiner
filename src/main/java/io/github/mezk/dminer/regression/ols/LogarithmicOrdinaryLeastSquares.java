package io.github.mezk.dminer.regression.ols;

import java.util.Arrays;

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
    public Result process(double[][] inputData) {

        if (Arrays.stream(inputData[0]).filter(x -> x < 0.0).toArray().length != 0) {
            throw new IllegalArgumentException("Input data contains argument "
                + "which is lower than zero");
        }

        final double[][] convertedInputData = new double[inputData[0].length][inputData[1].length];
        convertedInputData[0] = ArrayMath.log(inputData[0]);
        convertedInputData[1] = inputData[1];

        final Result linearOslResult = super.process(convertedInputData);
        final double a = linearOslResult.getCoefficientA();
        final double b = linearOslResult.getCoefficientB();

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
    public strictfp double[] calculateFunctionValues(
            double[] x, double a, double b) {
        final double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = a * StrictMath.log(x[i]) + b;
        }
        return result;
    }
}
