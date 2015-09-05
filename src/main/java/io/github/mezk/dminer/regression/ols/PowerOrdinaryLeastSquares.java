package io.github.mezk.dminer.regression.ols;

/**
 * Ordinary Least Squares method for power function.
 *
 * @author Andrei Selkin
 */
public class PowerOrdinaryLeastSquares extends LinearOrdinaryLeastSquares {

    @Override
    public  strictfp Result process(double[][] inputData) {

        final double[][] convertedInputData = new double[inputData[0].length][inputData[1].length];
        convertedInputData[0] = lnArray(inputData[0]);
        convertedInputData[1] = lnArray(inputData[1]);

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

    /**
     * Returns an array each element of which is a natural logarithm
     * of the original array.
     * @param array array.
     * @return array each element of which is a natural logarithm
     * of the original array.
     */
    public static double[] lnArray(double[] array) {
        final double[] result = new double[array.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = StrictMath.log(array[i]);
        }
        return result;
    }
}
