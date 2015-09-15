package io.github.mezk.dminer.regression.osl;

import org.junit.Assert;
import org.junit.Test;

public class HyperbolicOrdinaryLeastSquaresTest {
    @Test
    public void testProcess() {
        // y = 1 / 2x + 2
        double[][] inputData = {
            {1, 2, 3, 4, 5, 6},
            {2.5, 2.25, 2.166666667, 2.125, 2.1, 2.083333333},
        };
        final AbstractOrdinaryLeastSquares osl = new HyperbolicOrdinaryLeastSquares();
        final Result result = osl.process(inputData);

        Assert.assertEquals(0.5, result.getCoefficientA(), 0.0000001);
        Assert.assertEquals(2.0, result.getCoefficientB(), 0.0000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument() {
        // y = 1 / 2x + 2
        double[][] inputData = {
            {1, 2, 0, 4, 5, 6},
            {2.5, 2.25, 2.166666667, 2.125, 2.1, 2.083333333},
        };
        final AbstractOrdinaryLeastSquares osl = new HyperbolicOrdinaryLeastSquares();
        osl.process(inputData);
    }
}
