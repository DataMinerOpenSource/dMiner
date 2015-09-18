package io.github.mezk.dminer.regression.osl;

import io.github.mezk.dminer.utils.ArrayMath;
import io.github.mezk.dminer.utils.StatsUtils;

/**
 * Ordinary Least Squares method for power function.
 * Power function is a relation of the form
 * y = a * x ^ b
 *
 * @author Andrei Selkin
 * @author Vladislav Lisetskiy
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
    public strictfp double[] calculateFunctionValues(double[] x, double a, double b) {
        final double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = a * StrictMath.pow(x[i], b);
        }
        return result;
    }
}
