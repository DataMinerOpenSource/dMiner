package io.github.mezk.dminer.regression.ols;

import org.junit.Assert;
import org.junit.Test;

public class FractionalLinearOrdinaryLeastSquaresTest {
    @Test
    public void testProcess() {
        // y = 1 / (2x + 4)
        double[][] inputData = {
            {1, 2, 3, 4, 5, 6},
            {0.166666667, 0.125, 0.1, 0.083333333, 0.071428571, 0.0625},
        };
        final AbstractOrdinaryLeastSquares osl = new FractionalLinearOrdinaryLeastSquares();
        final Result result = osl.process(inputData);

        Assert.assertEquals(2.0, result.getCoefficientA(), 0.0000001);
        Assert.assertEquals(4.0, result.getCoefficientB(), 0.0000001);
    }
}
