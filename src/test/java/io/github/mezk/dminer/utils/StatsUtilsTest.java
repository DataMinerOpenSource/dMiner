package io.github.mezk.dminer.utils;

import org.junit.Test;

public class StatsUtilsTest {

    @Test
    public void testPrivateCtor() throws ReflectiveOperationException {
        TestUtils.assertUtilsClassHasPrivateConstructor(StatsUtils.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDotProductWrongArguments() {
        StatsUtils.correlation(new double[1], new double[2]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRootMeanSquaredError() {
        StatsUtils.rootMeanSquaredError(new double[1], new double[2]);
    }
}
