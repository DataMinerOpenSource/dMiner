package io.github.mezk.dminer.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.Assert;

/**
 * Contains helpers for Unit-testing.
 *
 * @author Vladislav Lisetskiy
 */
public final class TestUtils {

    /** Default constructor. */
    private TestUtils() { }

    /**
     * Verifies that utils class has private constructor
     * and invokes it to satisfy code coverage.
     * @param utilClass class to verify.
     * @throws ReflectiveOperationException if an reflection error occurs.
     */
    public static void assertUtilsClassHasPrivateConstructor(final Class<?> utilClass)
            throws ReflectiveOperationException {
        final Constructor<?> constructor = utilClass.getDeclaredConstructor();
        if (!Modifier.isPrivate(constructor.getModifiers())) {
            Assert.fail("Constructor is not private");
        }
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    /**
     * Asserts that each element of values array is not NaN or Infinite.
     * @param values values to check.
     */
    public static void assertNotNanOrInfinite(double[] values) {
        for (int i = 0; i < values.length; i++) {
            Assert.assertFalse(Double.isNaN(values[i]));
            Assert.assertFalse(Double.isInfinite(values[i]));
        }
    }
}
