package io.github.mezk.dminer.regression.osl;

import org.junit.Assert;
import org.junit.Test;

public class RationalOrdinaryLeastSquaresTest {
    @Test
    public void testProcess() {
        // y = x / (2x + 2)
        double[][] inputData = {
            {1, 2, 3, 4, 5, 6,},
            {0.25, 0.333333333, 0.375, 0.4, 0.416666667, 0.428571429},
        };
        final AbstractOrdinaryLeastSquares osl = new RationalOrdinaryLeastSquares();
        final Result result = osl.process(inputData);

        Assert.assertEquals(2.0, result.getCoefficientA(), 0.00000001);
        Assert.assertEquals(2.0, result.getCoefficientB(), 0.00000001);
    }
}
