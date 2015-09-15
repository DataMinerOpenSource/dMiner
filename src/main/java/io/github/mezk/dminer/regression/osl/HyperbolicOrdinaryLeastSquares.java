package io.github.mezk.dminer.regression.osl;

import org.apache.commons.lang3.ArrayUtils;

import io.github.mezk.dminer.utils.ArrayMath;
import io.github.mezk.dminer.utils.StatsUtils;

/**
 * Ordinary Least Squares method for hyperbolic function.
 *
 * @author Andrei Selkin
 * @author Vladislav Lisetskiy
 */
public class HyperbolicOrdinaryLeastSquares extends LinearOrdinaryLeastSquares {

    @Override
    public strictfp Result process(double[][] inputData) throws IllegalArgumentException {

        if (ArrayUtils.contains(inputData[0], 0.0)) {
            throw new IllegalArgumentException("x cannot be zero in hyperbolic function.");
        }

        final double[][] convertedInputData = new double[inputData[0].length][inputData[1].length];
        convertedInputData[0] = ArrayMath.pow(inputData[0], -1);
        convertedInputData[1] = inputData[1];

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
            result[i] = a / x[i] + b;
        }
        return result;
    }
}
