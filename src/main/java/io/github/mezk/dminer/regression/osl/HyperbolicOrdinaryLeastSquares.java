package io.github.mezk.dminer.regression.osl;

import org.apache.commons.lang3.ArrayUtils;

import io.github.mezk.dminer.utils.ArrayMath;

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

        //TODO: Calculate correlation coefficient.

        final Result result = new Result();
        result.setCoefficientA(a);
        result.setCoefficientB(b);
        return result;
    }
}
