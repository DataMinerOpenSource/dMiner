package io.github.mezk.dminer.regression.osl;

import org.junit.Assert;
import org.junit.Test;

public class LinearOrdinaryLeastSquaresTest {

    @Test
    public void testProcess() {
        // y = 2x + 4
        double[][] inputData = {
            {1, 2, 3, 4, 5, 6,},
            {6, 8, 10, 12, 14, 16},
        };
        final OrdinaryLeastSquares osl = new LinearOrdinaryLeastSquares();
        final Result result = osl.process(inputData);

        Assert.assertEquals(2.0, result.getCoefficientA(), 0.0000000000000001);
        Assert.assertEquals(4.0, result.getCoefficientB(), 0.0000000000000001);
        Assert.assertTrue(result.getCorrelationCoefficient() > 0.7);
    }
}
