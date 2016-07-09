package io.github.mezk.dminer.utils;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SequenceGeneratorsTest {

    @Test
    public void testPrivateConstructor() throws ReflectiveOperationException {
        TestUtils.assertUtilsClassHasPrivateConstructor(SequenceGenerators.class);
    }

    @Test
    public void testGenerateIntSequence() {
        final int[] array1 = SequenceGenerators.generateIntSequence(10);
        final int[] expected1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected1, array1);
        final int[] array2 = SequenceGenerators.generateIntSequence(1, 5);
        final int[] expected2 = {1, 2, 3, 4, 5};
        assertArrayEquals(expected2, array2);
    }

}
