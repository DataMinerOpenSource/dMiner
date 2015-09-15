package io.github.mezk.dminer.utils;

import org.junit.Test;

import io.github.mezk.dminer.TestUtils;

public class StatsUtilsTest {

    @Test
    public void testPrivateCtor() throws ReflectiveOperationException {
        TestUtils.assertUtilsClassHasPrivateConstructor(StatsUtils.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDotProductWrongArguments() {
        StatsUtils.calculateLinearCorrelationCoefficient(new double[1], new double [2]);
    }
}
