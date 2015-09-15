package io.github.mezk.dminer.regression.osl;

import org.junit.Assert;
import org.junit.Test;

public class LogarithmicOrdinaryLeastSquaresTest {
    @Test
    public void testProcess() {
        // y = 2 * ln(x) + 4
        double[][] inputData = {
            {1, 2, 3, 4, 5, 6},
            {4.0, 5.3862943611, 6.1972245773, 6.7725887222, 7.2188758249, 7.5835189385},
        };
        final AbstractOrdinaryLeastSquares osl = new LogarithmicOrdinaryLeastSquares();
        final Result result = osl.process(inputData);

        Assert.assertEquals(2.0, result.getCoefficientA(), 0.0000000001);
        Assert.assertEquals(4.0, result.getCoefficientB(), 0.0000000001);
        Assert.assertTrue(result.getCorrelationCoefficient() > 0.7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInputDataContainsArgumentWhichIsLowerThanZero() {
        // y = 2 * ln(x) + 4
        double[][] inputData = {
            {-1, 2, 3, 4, 5, 6},
            {4.0, 5.3862943611, 6.1972245773, 6.7725887222, 7.2188758249, 7.5835189385},
        };
        final AbstractOrdinaryLeastSquares osl = new LogarithmicOrdinaryLeastSquares();
        final Result result = osl.process(inputData);
    }
}
