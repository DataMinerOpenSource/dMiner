package io.github.mezk.dminer.regression.osl;

import io.github.mezk.dminer.utils.ArrayMath;

/**
 * Ordinary Least Squares method for rational function.
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

        //TODO: Calculate correlation coefficient.

        final Result result = new Result();
        result.setCoefficientA(a);
        result.setCoefficientB(b);
        return result;
    }
}
