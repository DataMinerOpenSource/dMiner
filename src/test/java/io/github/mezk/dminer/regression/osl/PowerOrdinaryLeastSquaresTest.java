package io.github.mezk.dminer.regression.osl;

import org.junit.Assert;
import org.junit.Test;

public class PowerOrdinaryLeastSquaresTest {
    @Test
    public void testProcess() {
        // y = 2x^2
        double[][] inputData = {
            {1, 2, 3, 4, 5, 6},
            {2, 8, 18, 32, 50, 72},
        };
        final AbstractOrdinaryLeastSquares osl = new PowerOrdinaryLeastSquares();
        final Result result = osl.process(inputData);

        Assert.assertEquals(2.0, result.getCoefficientA(), 0.0000000000000001);
        Assert.assertEquals(2.0, result.getCoefficientB(), 0.0000000000000001);
        Assert.assertTrue(result.getCorrelationCoefficient() > 0.7);
    }
}
