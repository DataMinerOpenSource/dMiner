package io.github.mezk.dminer.regression.ols;

import edu.stanford.nlp.math.ArrayMath;

/**
 * Ordinary Least Squares method for rational function.
 *
 * @author Andrei Selkin
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

        //TODO: Calculate correlation coefficient.

        final Result result = new Result();
        result.setCoefficientA(a);
        result.setCoefficientB(b);
        return result;
    }
}
