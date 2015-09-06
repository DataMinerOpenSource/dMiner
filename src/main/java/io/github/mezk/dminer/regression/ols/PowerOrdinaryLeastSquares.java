package io.github.mezk.dminer.regression.ols;

import edu.stanford.nlp.math.ArrayMath;

/**
 * Ordinary Least Squares method for power function.
 *
 * @author Andrei Selkin
 */
public class PowerOrdinaryLeastSquares extends LinearOrdinaryLeastSquares {

    @Override
    public  strictfp Result process(double[][] inputData) {

        final double[][] convertedInputData = new double[inputData[0].length][inputData[1].length];
        convertedInputData[0] = ArrayMath.log(inputData[0]);
        convertedInputData[1] = ArrayMath.log(inputData[1]);

        final Result linearOslResult = super.process(convertedInputData);
        final double a = linearOslResult.getCoefficientA();
        final double convertedB = linearOslResult.getCoefficientB();
        final double b = StrictMath.exp(convertedB);

        //TODO: Calculate correlation coefficient.

        final Result result = new Result();
        result.setCoefficientA(a);
        result.setCoefficientB(b);
        return result;
    }
}
