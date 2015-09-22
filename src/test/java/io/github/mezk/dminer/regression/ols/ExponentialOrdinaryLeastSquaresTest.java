package io.github.mezk.dminer.regression.ols;

import org.junit.Assert;
import org.junit.Test;

public class ExponentialOrdinaryLeastSquaresTest {
    @Test
    public void testProcess() {
        // y = 2e^(2x)
        double[][] inputData = {
            {1, 2, 3, 4, 5, 6,},
            {14.778112198, 109.196300066, 806.857586985,
             5961.915974083, 44052.931589613, 325509.582838008},
        };
        final OrdinaryLeastSquares osl = new ExponentialOrdinaryLeastSquares();
        final Result result = osl.process(inputData);

        Assert.assertEquals(2.0, result.getCoefficientA(), 0.00000000001);
        Assert.assertEquals(2.0, result.getCoefficientB(), 0.00000000001);
        Assert.assertTrue(result.getCorrelationCoefficient() > 0.7);
    }
}
