package io.github.mezk.dminer.regression.ols;

import org.junit.Assert;
import org.junit.Test;

import io.github.mezk.dminer.utils.TestUtils;

public class ExponentialOrdinaryLeastSquaresTest {

    @Test
    public void testRegressStrongExponentialFunction() {
        // y = 2e^(2x)
        final double[][] inputData = {
            {1, 2, 3, 4, 5, 6,},
            {14.778112198, 109.196300066, 806.857586985,
             5961.915974083, 44052.931589613, 325509.582838008},
        };
        final OrdinaryLeastSquares osl = new ExponentialOrdinaryLeastSquares();
        final RegressionResults results = osl.regress(inputData);
        final double[] predictedFunctionValues = results.getPredictedFunctionValues();

        Assert.assertEquals(2.0, results.getCoefficientA(), 0.00000000001);
        Assert.assertEquals(2.0, results.getCoefficientB(), 0.00000000001);
        Assert.assertTrue(results.getCorrelationCoefficient() > 0.7);
        Assert.assertEquals(7.755330274884711E-6, results.getRootMeanSquaredError(), Double.MIN_NORMAL);
        Assert.assertArrayEquals(inputData[1], predictedFunctionValues, 0.0001);
    }

    @Test
    public void testPredictFunctionValuesNotNaNorInfiniteVar1() {
        final double[][] inputData = {
            {1, 2, 3, 10, 9, 7, 4, 5, 5, 6, 3, 11},
            {0.5, 1.5, 1, 3, 4, 3, 1, 2.5, 3, 1.5, 5, 0.5},
        };

        final OrdinaryLeastSquares osl = new ExponentialOrdinaryLeastSquares();
        final RegressionResults results = osl.regress(inputData);
        final double[] predictedFunctionValues = results.getPredictedFunctionValues();

        TestUtils.assertNotNanOrInfinite(predictedFunctionValues);
    }

    @Test
    public void testPredictFunctionValuesNotNaNorInfiniteVar2() {
        final double[][] inputData = {
            {10.421, 10.939, 11.321, 11.799, 12.242, 12.668},
            {10.5, 29.49, 42.7, 60.01, 75.5, 91.05},
        };

        final OrdinaryLeastSquares osl = new ExponentialOrdinaryLeastSquares();
        final RegressionResults results = osl.regress(inputData);
        final double[] predictedFunctionValues = results.getPredictedFunctionValues();

        TestUtils.assertNotNanOrInfinite(predictedFunctionValues);
    }

    @Test
    public void testDoubleOverflowVar3() {
        final double[][] inputData = {
            {273, 286, 288, 293, 313, 333, 353, 373},
            {29.4, 33.3, 35.2, 37.2, 45.8, 55.2, 65.6, 77.8},
        };

        final OrdinaryLeastSquares osl = new ExponentialOrdinaryLeastSquares();
        final RegressionResults results = osl.regress(inputData);
        final double[] actuals = results.getPredictedFunctionValues();

        final double[] expecteds = {
            1.1630306875472762E248,
            9.419369338083093E259,
            6.398645947712553E261,
            2.433625322518321E266,
            5.09232668906768E284,
            1.0655621828161973E303,
            // Double overflow
            Double.POSITIVE_INFINITY,
            Double.POSITIVE_INFINITY,
        };

        Assert.assertArrayEquals(expecteds, actuals, Double.MIN_NORMAL);
    }

    @Test
    public void testPredictFunctionValuesNotNaNorInfiniteVar4() {
        final double[][] inputData = {
            {0, 1, 2, 3, 4, 5, 6},
            {10, 4.97, 2.47, 1.22, 0.61, 0.3, 0.14},
        };

        final OrdinaryLeastSquares osl = new ExponentialOrdinaryLeastSquares();
        final RegressionResults results = osl.regress(inputData);
        final double[] predictedFunctionValues = results.getPredictedFunctionValues();

        TestUtils.assertNotNanOrInfinite(predictedFunctionValues);
    }

    @Test
    public void testDoubleOverflowVar5() {
        final double[][] inputData = {
            {2, 5, 8, 11, 14, 17, 27, 31, 35, 44},
            {94.8, 87.9, 81.3, 74.9, 38.7, 34.0, 49.3, 44, 39.1, 31.6},
        };
        final OrdinaryLeastSquares osl = new ExponentialOrdinaryLeastSquares();
        final RegressionResults results = osl.regress(inputData);

        final double[] actuals = results.getPredictedFunctionValues();
        final double[] expecteds = {
            -4.554418755712663E71,
            -3.801679279008597E181,
            -3.1733501277885446E291,
            // Double overflow
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
        };

        Assert.assertArrayEquals(expecteds, actuals, Double.MIN_NORMAL);
    }

    @Test
    public void testDoubleOverflowVar6() {
        final double[][] inputData = {
            {8, 10, 15, 20, 30, 40, 60, 80},
            {13, 14, 15.4, 16.3, 17.2, 17.8, 18.5, 18.8},
        };
        final OrdinaryLeastSquares osl = new ExponentialOrdinaryLeastSquares();
        final RegressionResults results = osl.regress(inputData);

        final double[] actuals = results.getPredictedFunctionValues();
        final double[] expecteds = {
            2.4900017674392524E46,
            3.825389224042873E58,
            1.1190914227021407E89,
            3.2738253260460935E119,
            2.8017886907030152E180,
            2.397812676473027E241,
            // Double overflow
            Double.POSITIVE_INFINITY,
            Double.POSITIVE_INFINITY,
        };

        Assert.assertArrayEquals(expecteds, actuals, Double.MIN_NORMAL);
    }

    @Test
    public void testPredictFunctionValuesNotNaNorInfiniteVar7() {
        final double[][] inputData = {
            {2.46, 2.97, 3.45, 3.96, 4.97, 5.97, 6.97, 7.97},
            {67.7, 65, 63, 61, 58.25, 56.25, 55.1, 54.3},
        };

        final OrdinaryLeastSquares osl = new ExponentialOrdinaryLeastSquares();
        final RegressionResults results = osl.regress(inputData);
        final double[] predictedFunctionValues = results.getPredictedFunctionValues();

        TestUtils.assertNotNanOrInfinite(predictedFunctionValues);
    }

    @Test
    public void testPredictFunctionValuesNotNaNorInfiniteVar8() {
        final double[][] inputData = {
            {36.8, 31.5, 26.3, 21, 15.8, 12.6, 8.4},
            {5.67, 5.85, 5.94, 6.03, 6.4, 6.58, 7.39},
        };

        final OrdinaryLeastSquares osl = new ExponentialOrdinaryLeastSquares();
        final RegressionResults results = osl.regress(inputData);
        final double[] predictedFunctionValues = results.getPredictedFunctionValues();

        TestUtils.assertNotNanOrInfinite(predictedFunctionValues);
    }

    @Test
    public void testDoubleOverflowVar9() {
        final double[][] inputData = {
            {2.5, 3.0, 3.1, 3.8, 7.0, 9.5, 11.3, 17.5, 51.5, 64.0, 95.0},
            {3.5, 5.0, 7.5, 10.0, 12.5, 13.5, 14.0, 15.0, 16.0, 17.0, 17.5},
        };
        final OrdinaryLeastSquares osl = new ExponentialOrdinaryLeastSquares();
        final RegressionResults results = osl.regress(inputData);

        final double[] actuals = results.getPredictedFunctionValues();
        final double[] expecteds = {
            1.222785814194774E7,
            7.980724780850618E8,
            1.8406902958147113E9,
            6.390714881741514E11,
            2.6277126633567622E23,
            3.1119856547702897E32,
            1.0615209732752146E39,
            3.3737171274667264E61,
            8.455917831199108E184,
            1.9699686550380407E230,
            // Double overflow
            Double.POSITIVE_INFINITY,
        };

        Assert.assertArrayEquals(expecteds, actuals, Double.MIN_NORMAL);
    }

    @Test
    public void testDoubleOverflowVar10() {
        final double[][] inputData = {
            {12.3, 11.5, 11.0, 12.0, 13.5, 12.5, 12.8, 9.9, 12.2, 12.5, 13.0, 10.5},
            {795, 915, 965, 892, 585, 644, 714, 1180, 851, 779, 625, 1001},
        };
        final OrdinaryLeastSquares osl = new ExponentialOrdinaryLeastSquares();
        final RegressionResults results = osl.regress(inputData);

        final double[] actuals = results.getPredictedFunctionValues();
        final double[] expecteds = {
            // Double overflow
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
        };

        Assert.assertArrayEquals(expecteds, actuals, Double.MIN_NORMAL);
    }
}
