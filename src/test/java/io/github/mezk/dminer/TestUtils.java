package io.github.mezk.dminer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.Assert;

public class TestUtils {

    private TestUtils() {
    }

    /**
     * Verifies that utils class has private constructor and invokes it to satisfy code coverage.
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
