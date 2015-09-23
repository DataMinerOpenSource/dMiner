package io.github.mezk.dminer.utils;

import org.junit.Test;

public class ArrayMathTest {

    @Test
    public void testPrivateCtor() throws ReflectiveOperationException {
        TestUtils.assertUtilsClassHasPrivateConstructor(ArrayMath.class);
    }

    @Test(expected = RuntimeException.class)
    public void testDotProductWrongArguments() {
        ArrayMath.dotProduct(new double[1], new double [2]);
    }
}
