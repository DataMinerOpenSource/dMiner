package io.github.mezk.dminer.regression.ols;

import org.junit.Assert;
import org.junit.Test;

public class LinearOrdinaryLeastSquaresTest {

    @Test
    public void testProcess() {
        double[][] inputData = {
            {1, 2, 3, 4, 5, 6,},
            {5.2, 6.3, 7.1, 8.5, 9.2, 10.0},
        };
        LinearOrdinaryLeastSquares osl = new LinearOrdinaryLeastSquares();
        osl.process(inputData);

        Assert.assertEquals(0.9742857142857139, osl.getResult().getCoefficientA(), 0.0000000000000001);
        Assert.assertEquals(4.3066666666666675, osl.getResult().getCoefficientB(), 0.0000000000000001);
    }

    @Test
    public void testSum() {
        double[] data = {1,2,3,};
        LinearOrdinaryLeastSquares osl = new LinearOrdinaryLeastSquares();
        Assert.assertEquals(6.0, osl.sum(data), 0.01);
    }

    @Test
    public void testSumOfProducts() {
        double[] data1 = {1,2,3,};
        double[] data2 = {1,2,3,};
        LinearOrdinaryLeastSquares osl = new LinearOrdinaryLeastSquares();
        Assert.assertEquals(14.0, osl.sumOfProducts(data1, data2), 0.01);
    }
}
