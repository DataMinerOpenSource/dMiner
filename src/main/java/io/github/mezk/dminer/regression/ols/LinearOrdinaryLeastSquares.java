package io.github.mezk.dminer.regression.ols;

import edu.stanford.nlp.math.ArrayMath;

/**
 * Ordinary Least Squares method for linear function.
 *
 * @author Andrei Selkin
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

        //TODO: Calculate correlation coefficient.

        final Result result = new Result();
        result.setCoefficientA(a);
        result.setCoefficientB(b);
        return result;
    }
}
