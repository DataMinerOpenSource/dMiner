package io.github.mezk.dminer.regression.ols;

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
        final LinearOrdinaryLeastSquares osl = new LinearOrdinaryLeastSquares();
        final Result result = osl.process(inputData);

        Assert.assertEquals(2.0, result.getCoefficientA(), 0.0000000000000001);
        Assert.assertEquals(4.0, result.getCoefficientB(), 0.0000000000000001);
    }
}
