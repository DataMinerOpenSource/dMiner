package io.github.mezk.dminer.regression.osl;

import io.github.mezk.dminer.utils.ArrayMath;
import io.github.mezk.dminer.utils.StatsUtils;

/**
 * Ordinary Least Squares method for rational function.
 *
 * @author Andrei Selkin
 * @author Vladislav Lisetskiy
 */
public class FractionalLinearOrdinaryLeastSquares extends LinearOrdinaryLeastSquares {

    @Override
    public strictfp Result process(double[][] inputData) {

        final double[][] convertedInputData = new double[inputData[0].length][inputData[1].length];
        convertedInputData[0] = inputData[0];
        convertedInputData[1] = ArrayMath.pow(inputData[1], -1);

        final Result linearOslResult = super.process(convertedInputData);
        final double a = linearOslResult.getCoefficientA();
        final double b = linearOslResult.getCoefficientB();

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
            result[i] = 1 / (a * x[i] + b);
        }
        return result;
    }
}
