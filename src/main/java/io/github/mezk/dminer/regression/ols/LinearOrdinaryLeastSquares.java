package io.github.mezk.dminer.regression.ols;

/**
 * Ordinary Least Squares method for linear function.
 *
 * @author Andrei Selkin
 */
public class LinearOrdinaryLeastSquares extends AbstractOrdinaryLeastSquares {

    /**Result of calculations.*/
    private Result result;

    /**Default constructor.*/
    public LinearOrdinaryLeastSquares() {
        result = new Result();
    }

    public Result getResult() {
        return result;
    }

    @Override
    public strictfp Result process(double[][] inputData) {

        final double sumX = sum(inputData[0]);
        final double sumY = sum(inputData[1]);
        final double sumXX = sumOfProducts(inputData[0], inputData[0]);
        final double sumXY = sumOfProducts(inputData[0], inputData[1]);
        final double squaredSumX = StrictMath.pow(sumX, 2);

        final double n = inputData[0].length;
        final double a = (n * sumXY - sumX * sumY) / (n * sumXX - squaredSumX);
        final double b = (sumY - a * sumX) / n;

        //TODO: Calculate correlation coefficient.

        result.setCoefficientA(a);
        result.setCoefficientB(b);
        return result;
    }
}
