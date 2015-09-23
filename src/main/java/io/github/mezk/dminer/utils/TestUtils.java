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
}
